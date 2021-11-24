package br.com.purple.api.converter.cliente.credencial;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.cliente.credencial.role.RoleDtoRoleConverter;
import br.com.purple.api.core.entity.model.CredencialCliente;
import br.com.purple.api.core.entity.model.Role;
import br.com.purple.api.dto.cliente.credencial.CredencialDTO;
import br.com.purple.api.dto.cliente.credencial.role.RoleDTO;

public class CredencialDTOCredencialConverter implements Converter<CredencialDTO, CredencialCliente> {

    private Converter<RoleDTO, Role> roleDTORoleConverter = new RoleDtoRoleConverter();

    @Override
    public CredencialCliente from(CredencialDTO credencialDTO) {
       CredencialCliente credencialCliente = new CredencialCliente();

       credencialCliente.setUsuario(credencialDTO.getUsuario());
       credencialCliente.setSenha(credencialDTO.getSenha());
       credencialCliente.setRoles(roleDTORoleConverter.from(credencialDTO.getRoles()));

        return credencialCliente;
    }
}
