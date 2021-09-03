package com.gmail.gabrielcacarvalho.RestApi.dto.endereco;

import lombok.Data;

@Data
public class EntradaEnderecoDTO {

    private String estado;
    private String cidade;
    private String bairro;
    private String cep;
    private String rua;
    private String numero;
}
