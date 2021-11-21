package br.com.purple.api.dto.pedido.item;

import br.com.purple.api.dto.enumerator.TamanhoDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDTO {

    private Integer id;
    private Integer idProduto;
    private Integer quantidade;
    private BigDecimal valorTotal;
    private TamanhoDTO tamanhoDTO;
}
