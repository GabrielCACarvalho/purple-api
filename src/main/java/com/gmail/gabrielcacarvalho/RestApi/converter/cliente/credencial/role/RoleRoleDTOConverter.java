package com.gmail.gabrielcacarvalho.RestApi.converter.cliente.credencial.role;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Role;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial.role.RoleDTO;

public class RoleRoleDTOConverter implements Converter<Role, RoleDTO> {
    @Override
    public RoleDTO from(Role role) {
        RoleDTO roleDTO = new RoleDTO();

        role.setId(role.getId());
        role.setNome(role.getNome());

        return roleDTO;
    }
}
