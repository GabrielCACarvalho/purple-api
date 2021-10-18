package com.gmail.gabrielcacarvalho.RestApi.converter.cliente;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Cliente;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.AlteraClienteDTO;

public class AlteraDTOClienteConverter implements Converter<AlteraClienteDTO, Cliente> {

    @Override
    public Cliente from(AlteraClienteDTO alteraClienteDTO) {
        Cliente cliente = new Cliente();

        cliente.setId(alteraClienteDTO.getId());
        cliente.setSexo(alteraClienteDTO.getSexo());
        cliente.setNome(alteraClienteDTO.getNome());
        cliente.setCpf(alteraClienteDTO.getCpf());
        cliente.setCelular(alteraClienteDTO.getCelular());

        return cliente;
    }
}
