package br.com.purple.api.service.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class FiltroCalculoPrecoPrazoProduto {

    @NotNull
    @NotBlank
    @ApiModelProperty(
            allowableValues = "04014 - SEDEX à vista / " +
                    "04510 - PAC à vista / " +
                    "04782 - SEDEX 12 ( à vista) / " +
                    "04790 - SEDEX 10 (à vista) / " +
                    "04804 - SEDEX Hoje à vista"
    )
    private String codigoServico;

    @NotNull
    @NotBlank
    @ApiModelProperty(
            example = "\"14031210\""
    )
    private String cepDestino;

    @NotNull
    @NotBlank
    @ApiModelProperty(
            example = "1.5"
    )
    private BigDecimal peso;

    @NotNull
    @NotBlank
    @ApiModelProperty(
            example = "20"
    )
    private BigDecimal comprimento;

    @NotNull
    @NotBlank
    @ApiModelProperty(
            example = "25"
    )
    private BigDecimal altura;

    @NotNull
    @NotBlank
    @ApiModelProperty(
            example = "10"
    )
    private BigDecimal largura;
}
