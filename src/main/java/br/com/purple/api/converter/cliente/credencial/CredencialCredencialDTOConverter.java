package br.com.purple.api.converter.cliente.credencial;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.cliente.credencial.role.RoleRoleDTOConverter;
import br.com.purple.api.core.entity.model.CredencialCliente;
import br.com.purple.api.core.entity.model.Role;
import br.com.purple.api.dto.cliente.credencial.CredencialClienteDTO;
import br.com.purple.api.dto.cliente.credencial.role.RoleDTO;

public class CredencialCredencialDTOConverter implements Converter<CredencialCliente, CredencialClienteDTO> {

    private Converter<Role, RoleDTO> roleRoleDTOConverter = new RoleRoleDTOConverter();

    @Override
    public CredencialClienteDTO from(CredencialCliente credencialCliente) {
        CredencialClienteDTO credencialClienteDTO = new CredencialClienteDTO();

        credencialClienteDTO.setUsuario(credencialCliente.getUsuario());
        credencialClienteDTO.setSenha(credencialCliente.getSenha());
        credencialClienteDTO.setRoles(roleRoleDTOConverter.from(credencialCliente.getRoles()));

        return credencialClienteDTO;
    }
}
