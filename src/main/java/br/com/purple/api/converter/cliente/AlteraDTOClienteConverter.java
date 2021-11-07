package br.com.purple.api.converter.cliente;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Cliente;
import br.com.purple.api.dto.cliente.AlteraClienteDTO;

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
