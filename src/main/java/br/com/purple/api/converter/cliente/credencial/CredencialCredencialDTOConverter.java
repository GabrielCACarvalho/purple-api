package br.com.purple.api.converter.cliente.credencial;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.cliente.credencial.role.RoleRoleDTOConverter;
import br.com.purple.api.core.entity.model.CredencialCliente;
import br.com.purple.api.core.entity.model.Role;
import br.com.purple.api.dto.cliente.credencial.CredencialDTO;
import br.com.purple.api.dto.cliente.credencial.role.RoleDTO;

public class CredencialCredencialDTOConverter implements Converter<CredencialCliente, CredencialDTO> {

    private Converter<Role, RoleDTO> roleRoleDTOConverter = new RoleRoleDTOConverter();

    @Override
    public CredencialDTO from(CredencialCliente credencialCliente) {
        CredencialDTO credencialDTO = new CredencialDTO();

        credencialDTO.setUsuario(credencialCliente.getUsuario());
        credencialDTO.setSenha(credencialCliente.getSenha());
        credencialDTO.setRoles(roleRoleDTOConverter.from(credencialCliente.getRoles()));

        return credencialDTO;
    }
}
