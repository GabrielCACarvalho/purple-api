package com.gmail.gabrielcacarvalho.RestApi.dto.promocao;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FiltroListarPromocoes {

    private Date dataInicio;
    private Date dataFim;
}
