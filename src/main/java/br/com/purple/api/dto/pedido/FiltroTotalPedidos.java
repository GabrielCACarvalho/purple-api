package br.com.purple.api.dto.pedido;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FiltroTotalPedidos {

    @ApiModelProperty("yyyy-mm-dd")
    private String dataInicio;
    @ApiModelProperty("yyyy-mm-dd")
    private String dataFim;
}
