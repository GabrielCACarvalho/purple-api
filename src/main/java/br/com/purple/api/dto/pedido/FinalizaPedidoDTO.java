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
    @ApiModelProperty(
            allowableValues = "04014 - SEDEX à vista / " +
                    "04510 - PAC à vista / " +
                    "04782 - SEDEX 12 ( à vista) / " +
                    "04790 - SEDEX 10 (à vista) / " +
                    "04804 - SEDEX Hoje à vista"
    )
    private String servicoEnvio;
}
