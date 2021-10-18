package com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class CredencialClienteDTO {
    private String usuario;
    private String senha;
    private Collection<Role> roles = new ArrayList<>();
}
