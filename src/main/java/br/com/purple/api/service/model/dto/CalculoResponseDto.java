package br.com.purple.api.service.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalculoResponseDto {

    private BigDecimal valor;
    private Integer prazoEntregaEmDias;
    private String codigoErro;
    private String mensagemErro;
}
