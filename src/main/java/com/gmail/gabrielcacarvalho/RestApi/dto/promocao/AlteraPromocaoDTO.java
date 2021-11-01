package com.gmail.gabrielcacarvalho.RestApi.dto.promocao;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AlteraPromocaoDTO {

    private Integer id;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private BigDecimal porcentagemDesconto;
}