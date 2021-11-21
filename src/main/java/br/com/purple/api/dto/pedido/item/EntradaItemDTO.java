package br.com.purple.api.dto.pedido.item;

import br.com.purple.api.dto.enumerator.TamanhoDTO;
import lombok.Data;

@Data
public class EntradaItemDTO {

    private Integer idProduto;
    private Integer quantidade;
    private TamanhoDTO tamanhoDTO;
}
