package br.com.purple.api.converter.cliente.credencial.role;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Role;
import br.com.purple.api.dto.cliente.credencial.role.RoleDTO;

public class RoleRoleDTOConverter implements Converter<Role, RoleDTO> {
    @Override
    public RoleDTO from(Role role) {
        RoleDTO roleDTO = new RoleDTO();

        role.setId(role.getId());
        role.setNome(role.getNome());

        return roleDTO;
    }
}
