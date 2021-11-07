package br.com.purple.api.converter.cliente.credencial;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.CredencialCliente;
import br.com.purple.api.dto.cliente.credencial.CredencialClienteDTO;

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
