package br.com.purple.api.dto.cliente.credencial;

import br.com.purple.api.core.entity.model.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class CredencialClienteDTO {
    private String usuario;
    private String senha;
    private Collection<Role> roles = new ArrayList<>();
}
