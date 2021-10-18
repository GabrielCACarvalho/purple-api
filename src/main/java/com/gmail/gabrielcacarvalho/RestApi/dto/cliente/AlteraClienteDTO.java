package com.gmail.gabrielcacarvalho.RestApi.dto.cliente;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Sexo;
import lombok.Data;

@Data
public class AlteraClienteDTO {

    private Integer id;
    private Sexo sexo;
    private String nome;
    private String cpf;
    private String celular;
}
