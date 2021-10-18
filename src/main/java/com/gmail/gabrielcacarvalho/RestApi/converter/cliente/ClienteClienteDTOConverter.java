package com.gmail.gabrielcacarvalho.RestApi.converter.cliente;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.endereco.EnderecoEnderecoDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Cliente;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.ClienteDTO;

public class ClienteClienteDTOConverter implements Converter<Cliente, ClienteDTO> {

    private EnderecoEnderecoDTOConverter enderecoEnderecoDTOConverter = new EnderecoEnderecoDTOConverter();

    @Override
    public ClienteDTO from(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setSexo(cliente.getSexo());
        if (cliente.getEndereco() != null)
            clienteDTO.setEnderecoDTO(enderecoEnderecoDTOConverter.from(cliente.getEndereco()));
        clienteDTO.setDataCadastro(cliente.getDataCadastro());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setCelular(cliente.getCelular());

        return clienteDTO;
    }
}
