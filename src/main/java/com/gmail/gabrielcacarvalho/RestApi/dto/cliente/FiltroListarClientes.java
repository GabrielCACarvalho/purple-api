package com.gmail.gabrielcacarvalho.RestApi.dto.cliente;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Sexo;
import lombok.Data;

import java.util.Date;

@Data
public class FiltroListarClientes {
    private Sexo sexo;
    private Integer id;
}
