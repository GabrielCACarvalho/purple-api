package br.com.purple.api.dto.cliente.credencial;

import lombok.Data;

@Data
public class AlteraCredencialDTO {

    private String usuario;
    private String novaSenha;
}
