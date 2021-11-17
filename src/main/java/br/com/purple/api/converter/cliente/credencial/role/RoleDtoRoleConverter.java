package br.com.purple.api.converter.cliente.credencial.role;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Role;
import br.com.purple.api.dto.cliente.credencial.role.RoleDTO;

public class RoleDtoRoleConverter implements Converter<RoleDTO, Role> {
    @Override
    public Role from(RoleDTO roleDTO) {
        Role role = new Role();

        role.setId(roleDTO.getId());
        role.setNome(roleDTO.getNome());

        return role;
    }
}
