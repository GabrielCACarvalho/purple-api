package br.com.purple.api.converter.pedido;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Item;
import br.com.purple.api.dto.enumerator.TamanhoDTO;
import br.com.purple.api.dto.pedido.item.ItemDTO;

public class ItemItemDTOConverter implements Converter<Item, ItemDTO> {

    @Override
    public ItemDTO from(Item item) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setIdProduto(item.getProduto().getId());
        itemDTO.setQuantidade(itemDTO.getQuantidade());
        itemDTO.setValorTotal(item.getValorTotal());
        itemDTO.setId(itemDTO.getId());
        itemDTO.setTamanhoDTO(TamanhoDTO.valueOf(item.getTamanho().name()));

        return itemDTO;
    }
}
