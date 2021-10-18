package com.gmail.gabrielcacarvalho.RestApi.converter.cliente.credencial;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.CredencialCliente;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial.CredencialClienteDTO;

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
