package br.com.purple.api.converter.pedido;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Item;
import br.com.purple.api.core.entity.model.Pedido;
import br.com.purple.api.dto.pedido.PedidoDTO;
import br.com.purple.api.dto.pedido.item.ItemDTO;

public class PedidoPedidoDTOConverter implements Converter<Pedido, PedidoDTO> {

    private Converter<Item, ItemDTO> itemItemDTOConverter = new ItemItemDTOConverter();

    @Override
    public PedidoDTO from(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setAberto(pedido.getAberto());
        pedidoDTO.setValorTotal(pedido.getValorTotal());
        pedidoDTO.setCliente(pedido.getCliente());
        pedidoDTO.setItensDTO(itemItemDTOConverter.from(pedido.getItens()));
        pedidoDTO.setDataCriacao(pedido.getDataCriacao());
        pedidoDTO.setId(pedidoDTO.getId());

        return pedidoDTO;
    }
}
