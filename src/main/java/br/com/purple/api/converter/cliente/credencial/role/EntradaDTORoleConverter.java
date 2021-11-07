package br.com.purple.api.converter.cliente.credencial.role;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Role;
import br.com.purple.api.dto.cliente.credencial.role.EntradaRoleDTO;

public class EntradaDTORoleConverter implements Converter<EntradaRoleDTO, Role> {
    @Override
    public Role from(EntradaRoleDTO entradaRoleDTO) {
        Role role = new Role();

        role.setNome(entradaRoleDTO.getNome());

        return role;
    }
}
