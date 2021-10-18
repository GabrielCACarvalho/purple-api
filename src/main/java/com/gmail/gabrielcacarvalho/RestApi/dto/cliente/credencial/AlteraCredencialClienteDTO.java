package com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial;

import lombok.Data;

@Data
public class AlteraCredencialClienteDTO {

    private String usuario;
    private String novaSenha;
}
