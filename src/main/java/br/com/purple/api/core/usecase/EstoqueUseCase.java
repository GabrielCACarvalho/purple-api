package br.com.purple.api.core.usecase;

import br.com.purple.api.core.entity.model.EstoquePK;
import br.com.purple.api.core.entity.model.Produto;
import br.com.purple.api.dto.produto.EntradaEstoque;
import br.com.purple.api.repositories.EstoqueRepository;
import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.estoque.EstoqueDTOConverter;
import br.com.purple.api.core.entity.model.Estoque;
import br.com.purple.api.dto.estoque.EstoqueDTO;
import br.com.purple.api.dto.estoque.FiltroConsultaEstoque;
import br.com.purple.api.dto.estoque.SaidaEstoque;
import br.com.purple.api.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueUseCase {

    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    private Converter<Estoque, EstoqueDTO> estoqueDTOConverter = new EstoqueDTOConverter();

    public EstoqueDTO consultaEstoqueProduto(FiltroConsultaEstoque filtroConsultaEstoque) {

        return estoqueDTOConverter.from(Optional.ofNullable(estoqueRepository
                .getByIdProdutoIdAndIdTamanho(filtroConsultaEstoque.getIdProduto(),
                        filtroConsultaEstoque.getTamanho())).orElse(new Estoque()));
    }

    public List<EstoqueDTO> consultaEstoqueProduto(Integer idProduto) {

        return estoqueDTOConverter.from(estoqueRepository.getByIdProdutoId(idProduto));
    }

    public EstoqueDTO entradaNoEstoque(EntradaEstoque entradaEstoque){
        Estoque estoque = obtemEstoqueAtual(entradaEstoque);

        estoque.setQuantidadeEmEstoque(estoque.getQuantidadeEmEstoque() + entradaEstoque.getQuantidade());

        return estoqueDTOConverter.from(estoqueRepository.save(estoque));
    }

    public EstoqueDTO saidaNoEstoque(SaidaEstoque saidaEstoque) {
        Estoque estoque = estoqueRepository.getByIdProdutoIdAndIdTamanho(saidaEstoque.getIdProduto(), saidaEstoque.getTamanho());

        if (estoque == null)
            throw new RuntimeException("ESSE PRODUTO NAO TEM ESTOQUE"); //TODO: Fazer validação com java validation

        estoque.setQuantidadeEmEstoque(estoque.getQuantidadeEmEstoque() - saidaEstoque.getQuantidade());

        return estoqueDTOConverter.from(estoqueRepository.save(estoque));
    }

    public Estoque obtemEstoqueAtual(EntradaEstoque entradaEstoque){
        Estoque estoque = estoqueRepository.getByIdProdutoIdAndIdTamanho(entradaEstoque.getIdProduto(), entradaEstoque.getTamanho());

        if (estoque != null) {
            return estoque;
        }

        return criaNovoEstoque(entradaEstoque);
    }

    private Estoque criaNovoEstoque(EntradaEstoque entradaEstoque) {
        Estoque estoque = new Estoque();

        estoque.setId(criaEstoquePK(entradaEstoque));
        estoque.setQuantidadeEmEstoque(0);

        return estoque;
    }

    private EstoquePK criaEstoquePK(EntradaEstoque entradaEstoque) {
        EstoquePK estoquePK = new EstoquePK();

        Optional<Produto> optionalProduto = produtoRepository.findById(entradaEstoque.getIdProduto());

        Produto produto = new Produto();

        if (optionalProduto.isPresent()){
            produto = optionalProduto.get();
        }

        estoquePK.setProduto(produto);
        estoquePK.setTamanho(entradaEstoque.getTamanho());

        return estoquePK;
    }
}
