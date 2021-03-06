package br.com.purple.api.usecase;

import br.com.purple.api.config.security.util.ClienteAutenticadoUtil;
import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.pedido.PedidoFinalizadoDTOConverter;
import br.com.purple.api.converter.pedido.PedidoPedidoDTOConverter;
import br.com.purple.api.core.entity.enumerator.StatusPedido;
import br.com.purple.api.core.entity.enumerator.Tamanho;
import br.com.purple.api.core.entity.model.*;
import br.com.purple.api.dto.pedido.*;
import br.com.purple.api.dto.pedido.item.EntradaItemDTO;
import br.com.purple.api.repositories.*;
import br.com.purple.api.service.CalcPrecoPrazoClient;
import br.com.purple.api.service.model.FiltroCalculoPrecoPrazoProduto;
import br.com.purple.api.service.model.dto.CalculoResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class PedidoUseCase {

    private PedidoRepository pedidoRepository;
    private ItemRepository itemRepository;
    private ProdutoRepository produtoRepository;
    private ClienteRepository clienteRepository;
    private EstoqueRepository estoqueRepository;
    private CalcPrecoPrazoClient calcPrecoPrazoClient;

    private final Converter<Pedido, PedidoDTO> pedidoPedidoDTOConverter = new PedidoPedidoDTOConverter();

    private final Converter<Pedido, PedidoFinalizadoDTO> pedidoFinalizadoDTOConverter = new PedidoFinalizadoDTOConverter();

    public PedidoDTO criaPedido(EntradaItemDTO entradaItemDTO) {
        if (!existePedidoAberto()){
            Pedido pedido = new Pedido();

            pedido.setDataCriacao(Date.from(Instant.now()));
            pedido.setItens(Collections.singletonList(criaItem(entradaItemDTO)));
            pedido.setValorTotal(getValorTotalPedido(pedido.getItens()));
            pedido.setCliente(clienteRepository.findByCredencialClienteUsuario(ClienteAutenticadoUtil.getUsuarioClienteAutenticado()));
            pedido.setAberto(true);

            return pedidoPedidoDTOConverter.from(pedidoRepository.save(pedido));
        } else {
            Optional<Pedido> optionalPedido = pedidoRepository.findByAbertoAndClienteCredencialClienteUsuario(true, ClienteAutenticadoUtil.getUsuarioClienteAutenticado());

            Pedido pedido = new Pedido();

            if (optionalPedido.isPresent()){
                pedido = optionalPedido.get();
            }

            return pedidoPedidoDTOConverter.from(pedido);
        }
    }

    public PedidoDTO addItem(EntradaItemDTO entradaItemDTO) {
        Optional<Pedido> optionalPedido = pedidoRepository.findByAbertoAndClienteCredencialClienteUsuario(true, ClienteAutenticadoUtil.getUsuarioClienteAutenticado());

        Pedido pedido;

        if (optionalPedido.isPresent()){
            pedido = optionalPedido.get();
        } else {
            throw new RuntimeException("N??o foi possivel adicionar, pois o pedido n??o existe.");
        }

        pedido.getItens().add(criaItem(entradaItemDTO));

        return pedidoPedidoDTOConverter.from(pedido);
    }

    public PedidoFinalizadoDTO finalizaPedido(FinalizaPedidoDTO finalizaPedidoDTO) {
        Cliente cliente = clienteRepository.findByCredencialClienteUsuario(ClienteAutenticadoUtil.getUsuarioClienteAutenticado());

        if (cliente.getEndereco() == null)
            throw new RuntimeException("Cliente n??o tem endere??o cadastrado.");

        Optional<Pedido> optionalPedido = pedidoRepository.findByAbertoAndClienteCredencialClienteUsuario(true, ClienteAutenticadoUtil.getUsuarioClienteAutenticado());

        Pedido pedido;

        if (optionalPedido.isPresent()){
            pedido = optionalPedido.get();
        } else {
            throw new RuntimeException("N??o h?? pedido aberto para esse usu??rio.");
        }

        pedido.setValorTotal(getValorTotalPedidoComEntrega(finalizaPedidoDTO.getServicoEnvio(), pedido));
        pedido.setDataEnvio(finalizaPedidoDTO.getDataEnvio());
        pedido.setDataPagamento(finalizaPedidoDTO.getDataPagamento());
        pedido.setFormaPagamento(finalizaPedidoDTO.getFormaPagamento());
        pedido.setStatus(StatusPedido.SOLICITADO);
        pedido.setAberto(false);

        verificaEstoqueProdutos(pedido);

        return pedidoFinalizadoDTOConverter.from(pedidoRepository.save(pedido));
    }

    private BigDecimal getValorTotalPedidoComEntrega(String servicoEnvio, Pedido pedido) {
        Cliente cliente = clienteRepository.findByCredencialClienteUsuario(ClienteAutenticadoUtil.getUsuarioClienteAutenticado());

        FiltroCalculoPrecoPrazoProduto filtro = new FiltroCalculoPrecoPrazoProduto();

        filtro.setCodigoServico(servicoEnvio);
        filtro.setCepDestino(cliente.getEndereco().getCep());
        filtro.setAltura(BigDecimal.valueOf(15));
        filtro.setComprimento(BigDecimal.valueOf(30));
        filtro.setPeso(BigDecimal.valueOf(1.5));
        filtro.setLargura(BigDecimal.valueOf(20));

        CalculoResponseDto calculoResponseDto = calcPrecoPrazoClient.getCalculo(filtro);

        BigDecimal valorTotal = getValorTotalPedido(pedido.getItens());

        return valorTotal.add(calculoResponseDto.getValor());
    }


    public void cancelaPedido() {
        Optional<Pedido> optionalPedido = pedidoRepository.findByAbertoAndClienteCredencialClienteUsuario(true, ClienteAutenticadoUtil.getUsuarioClienteAutenticado());

        Pedido pedido;

        if (optionalPedido.isPresent()){
            pedido = optionalPedido.get();

            pedidoRepository.delete(pedido);
            pedido.getItens().forEach( item -> itemRepository.delete(item));
        }
    }

    private boolean existePedidoAberto() {
        Optional<Pedido> pedido = pedidoRepository.findByAbertoAndClienteCredencialClienteUsuario(true, ClienteAutenticadoUtil.getUsuarioClienteAutenticado());

        return pedido.isPresent();
    }

    private BigDecimal getValorTotalPedido(List<Item> itens) {

        BigDecimal somatoria = new BigDecimal(0);

        for (Item item: itens) {
            somatoria = somatoria.add(item.getValorTotal());
        }

        return somatoria;
    }

    private Item criaItem(EntradaItemDTO entradaItemDTO) {
        Optional<Produto> optionalProduto = produtoRepository.findById(entradaItemDTO.getIdProduto());

        Produto produto = new Produto();

        if (optionalProduto.isPresent()){
            produto = optionalProduto.get();
        }

        Item item = new Item();

        item.setProduto(produto);
        item.setQuantidade(entradaItemDTO.getQuantidade());
        item.setTamanho(Tamanho.valueOf(entradaItemDTO.getTamanhoDTO().name()));
        item.setValorTotal(BigDecimal.valueOf(produto.getValorUnitario().doubleValue() * entradaItemDTO.getQuantidade()));

        return itemRepository.save(item);
    }

    private void verificaEstoqueProdutos(Pedido pedido) {
        pedido.getItens().forEach(this::verificaEstoque);
    }

    private void verificaEstoque(Item item) {
        Estoque estoque = estoqueRepository.getByIdProdutoIdAndIdTamanho(item.getProduto().getId(), item.getTamanho());

        if (estoque == null)
            throw new RuntimeException("O produto de ID: " + item.getProduto().getId() + " n??o tem estoque.");

        if (estoque.getQuantidadeEmEstoque() < item.getQuantidade())
            throw new RuntimeException("O produto de ID: " + item.getProduto().getId() + " n??o possui essa quantidade em estoque.");

        estoque.setQuantidadeEmEstoque(estoque.getQuantidadeEmEstoque() - item.getQuantidade());

        estoqueRepository.save(estoque);
    }

    public PedidoDTO retiraItem(Integer idItem) {
        Optional<Pedido> optionalPedido = pedidoRepository.findByAbertoAndClienteCredencialClienteUsuario(true, ClienteAutenticadoUtil.getUsuarioClienteAutenticado());

        Pedido pedido;

        if (optionalPedido.isPresent()){
            pedido = optionalPedido.get();
        } else {
            throw new RuntimeException("N??o foi possivel retirar, pois o pedido n??o existe.");
        }

        pedido.getItens().remove(itemRepository.getById(idItem));
        itemRepository.deleteById(idItem);

        return pedidoPedidoDTOConverter.from(pedidoRepository.save(pedido));
    }

    public Long obterTotalPedidos(FiltroTotalPedidos filtro) {
        if (filtro.getDataInicio() != null & filtro.getDataFim() != null)
            return pedidoRepository.countByAbertoAndDataCriacaoBetween(false, Date.valueOf(filtro.getDataInicio()), Date.valueOf(filtro.getDataFim()));
        else
            return pedidoRepository.countByAberto(false);
    }

    public BigDecimal obterRendaTotal(FiltroRendaTotal filtro) {
        if (filtro.getDataInicio() != null & filtro.getDataFim() != null) {
            Double somatoria = pedidoRepository.selectSomatoriaValorTotalPedidos(Date.valueOf(filtro.getDataInicio()), Date.valueOf(filtro.getDataFim()));
            if (somatoria != null)
                return BigDecimal.valueOf(somatoria);
            else
                throw new RuntimeException("N??o pedidos para serem contados.");
        } else
            return BigDecimal.valueOf(pedidoRepository.selectSomatoriaValorTotalPedidos());
    }
}
