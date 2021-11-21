package br.com.purple.api.dto.pedido;

import br.com.purple.api.core.entity.enumerator.StatusPedido;
import br.com.purple.api.dto.pedido.item.ItemDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class PedidoFinalizadoDTO {

    private Integer id;
    private StatusPedido status;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataEnvio;
    private String formaPagamento;
    private List<ItemDTO> itensDTO;
    private BigDecimal valorTotal;
}
