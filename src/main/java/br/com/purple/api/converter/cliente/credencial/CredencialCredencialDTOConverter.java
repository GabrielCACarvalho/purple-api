package br.com.purple.api.converter.cliente.credencial;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.CredencialCliente;
import br.com.purple.api.dto.cliente.credencial.CredencialClienteDTO;

public class CredencialCredencialDTOConverter implements Converter<CredencialCliente, CredencialClienteDTO> {

    @Override
    public CredencialClienteDTO from(CredencialCliente credencialCliente) {
        CredencialClienteDTO credencialClienteDTO = new CredencialClienteDTO();

        credencialClienteDTO.setUsuario(credencialCliente.getUsuario());
        credencialClienteDTO.setSenha(credencialCliente.getSenha());
        credencialClienteDTO.setRoles(credencialCliente.getRoles());//TODO: Fazer um DTO para roles e alterar aqui

        return credencialClienteDTO;
    }
}
