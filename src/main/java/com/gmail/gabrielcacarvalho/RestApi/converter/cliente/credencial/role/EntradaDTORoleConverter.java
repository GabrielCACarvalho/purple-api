package com.gmail.gabrielcacarvalho.RestApi.converter.cliente.credencial.role;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Role;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial.role.EntradaRoleDTO;

public class EntradaDTORoleConverter implements Converter<EntradaRoleDTO, Role> {
    @Override
    public Role from(EntradaRoleDTO entradaRoleDTO) {
        Role role = new Role();

        role.setNome(entradaRoleDTO.getNome());

        return role;
    }
}
