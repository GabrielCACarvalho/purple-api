package br.com.purple.api.dto.cliente;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FiltroTotalClientes {

    @ApiModelProperty("yyyy-mm-dd")
    private String dataInicio;
    @ApiModelProperty("yyyy-mm-dd")
    private String dataFim;
}
