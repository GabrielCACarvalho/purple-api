package com.gmail.gabrielcacarvalho.RestApi.dto.cliente;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Sexo;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.EnderecoDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ClienteDTO {

    private Sexo sexo;
    private String nome;
    private String cpf;
    private String celular;
    private EnderecoDTO enderecoDTO;
    private Date dataCadastro;
}
