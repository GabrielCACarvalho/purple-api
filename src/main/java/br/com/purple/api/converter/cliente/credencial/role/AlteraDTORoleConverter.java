package br.com.purple.api.converter.cliente.credencial.role;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Role;
import br.com.purple.api.dto.cliente.credencial.role.AlteraRoleDTO;

public class AlteraDTORoleConverter implements Converter<AlteraRoleDTO, Role> {
    @Override
    public Role from(AlteraRoleDTO alteraRoleDTO) {
        Role role = new Role();

        role.setId(alteraRoleDTO.getId());
        role.setNome(alteraRoleDTO.getNome());

        return role;
    }
}
