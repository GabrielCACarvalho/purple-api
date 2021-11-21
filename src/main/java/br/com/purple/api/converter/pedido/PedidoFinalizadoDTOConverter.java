package br.com.purple.api.converter.pedido;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Item;
import br.com.purple.api.core.entity.model.Pedido;
import br.com.purple.api.dto.pedido.PedidoFinalizadoDTO;
import br.com.purple.api.dto.pedido.item.ItemDTO;
import org.springframework.security.core.parameters.P;

public class PedidoFinalizadoDTOConverter implements Converter<Pedido, PedidoFinalizadoDTO> {

    private Converter<Item, ItemDTO> itemItemDTOConverter = new ItemItemDTOConverter();

    @Override
    public PedidoFinalizadoDTO from(Pedido pedido) {
        PedidoFinalizadoDTO pedidoFinalizadoDTO = new PedidoFinalizadoDTO();

        pedidoFinalizadoDTO.setItensDTO(itemItemDTOConverter.from(pedido.getItens()));
        pedidoFinalizadoDTO.setDataCriacao(pedido.getDataCriacao());
        pedidoFinalizadoDTO.setDataEnvio(pedido.getDataEnvio());
        pedidoFinalizadoDTO.setDataPagamento(pedido.getDataPagamento());
        pedidoFinalizadoDTO.setId(pedido.getId());
        pedidoFinalizadoDTO.setStatus(pedido.getStatus());
        pedidoFinalizadoDTO.setFormaPagamento(pedido.getFormaPagamento());
        pedidoFinalizadoDTO.setValorTotal(pedido.getValorTotal());

        return pedidoFinalizadoDTO;
    }
}
