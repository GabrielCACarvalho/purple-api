package com.gmail.gabrielcacarvalho.RestApi.converter.cliente.credencial.role;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Role;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial.role.AlteraRoleDTO;

public class AlteraDTORoleConverter implements Converter<AlteraRoleDTO, Role> {
    @Override
    public Role from(AlteraRoleDTO alteraRoleDTO) {
        Role role = new Role();

        role.setId(alteraRoleDTO.getId());
        role.setNome(alteraRoleDTO.getNome());

        return role;
    }
}
