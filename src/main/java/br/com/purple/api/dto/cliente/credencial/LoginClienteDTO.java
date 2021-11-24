package br.com.purple.api.dto.cliente.credencial;

import lombok.Data;

@Data
public class LoginClienteDTO {
    private String usuario;
    private String senha;
}
