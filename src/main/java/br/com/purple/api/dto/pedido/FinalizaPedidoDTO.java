package br.com.purple.api.dto.pedido;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class FinalizaPedidoDTO {

    @ApiModelProperty(example = "yyyy-mm-dd")
    private Date dataEnvio;
    @ApiModelProperty(example = "yyyy-mm-dd")
    private Date dataPagamento;
    private String formaPagamento;
}
