package com.gmail.gabrielcacarvalho.RestApi.dto.promocao;

import lombok.Data;

import java.util.Date;

@Data

@FiltroListarPromocoesValido
public class FiltroListarPromocoes {

    private Date dataInicio;
    private Date dataFim;

}
