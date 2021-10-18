package com.gmail.gabrielcacarvalho.RestApi.converter.cliente.credencial;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.CredencialCliente;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial.CredencialClienteDTO;

public class CredencialDTOCredencialConverter implements Converter<CredencialClienteDTO, CredencialCliente> {
    @Override
    public CredencialCliente from(CredencialClienteDTO credencialClienteDTO) {
       CredencialCliente credencialCliente = new CredencialCliente();

       credencialCliente.setUsuario(credencialClienteDTO.getUsuario());
       credencialCliente.setSenha(credencialClienteDTO.getSenha());
       credencialCliente.setRoles(credencialCliente.getRoles());

        return credencialCliente;
    }
}
