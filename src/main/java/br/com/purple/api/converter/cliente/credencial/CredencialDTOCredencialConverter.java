package br.com.purple.api.converter.cliente.credencial;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.cliente.credencial.role.RoleDtoRoleConverter;
import br.com.purple.api.core.entity.model.CredencialCliente;
import br.com.purple.api.core.entity.model.Role;
import br.com.purple.api.dto.cliente.credencial.CredencialClienteDTO;
import br.com.purple.api.dto.cliente.credencial.role.RoleDTO;

public class CredencialDTOCredencialConverter implements Converter<CredencialClienteDTO, CredencialCliente> {

    private Converter<RoleDTO, Role> roleDTORoleConverter = new RoleDtoRoleConverter();

    @Override
    public CredencialCliente from(CredencialClienteDTO credencialClienteDTO) {
       CredencialCliente credencialCliente = new CredencialCliente();

       credencialCliente.setUsuario(credencialClienteDTO.getUsuario());
       credencialCliente.setSenha(credencialClienteDTO.getSenha());
       credencialCliente.setRoles(roleDTORoleConverter.from(credencialClienteDTO.getRoles()));

        return credencialCliente;
    }
}
