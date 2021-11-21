package br.com.purple.api.dto.pedido;

import br.com.purple.api.core.entity.model.Cliente;
import br.com.purple.api.dto.pedido.item.ItemDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class PedidoDTO {

    private Integer id;
    private List<ItemDTO> itensDTO;
    private Cliente cliente;
    private BigDecimal valorTotal;
    private Date dataCriacao;
    private Boolean aberto;
}
