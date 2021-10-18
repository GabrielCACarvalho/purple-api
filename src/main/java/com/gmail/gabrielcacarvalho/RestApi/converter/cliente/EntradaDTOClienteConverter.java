package com.gmail.gabrielcacarvalho.RestApi.converter.cliente;

import com.gmail.gabrielcacarvalho.RestApi.config.security.util.ClienteAutenticadoUtil;
import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.endereco.EntradaDTOEnderecoConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Cliente;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.CredencialCliente;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Endereco;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.EntradaClienteDTO;

public class EntradaDTOClienteConverter implements Converter<EntradaClienteDTO, Cliente> {

    private EntradaDTOEnderecoConverter entradaDTOEnderecoConverter = new EntradaDTOEnderecoConverter();

    @Override
    public Cliente from(EntradaClienteDTO entradaClienteDTO) {
        Cliente cliente = new Cliente();

        cliente.setNome(entradaClienteDTO.getNome());
        cliente.setSexo(entradaClienteDTO.getSexo());
        cliente.setCpf(entradaClienteDTO.getCpf());
        cliente.setCelular(entradaClienteDTO.getCelular());
        cliente.setCredencialCliente(new CredencialCliente());
        cliente.getCredencialCliente().setUsuario(ClienteAutenticadoUtil.getUsuarioClienteAutenticado());
        cliente.setDataCadastro(entradaClienteDTO.getDataCadastro());
        if (entradaClienteDTO.getEnderecoDTO() != null)
            cliente.setEndereco(entradaDTOEnderecoConverter.from(entradaClienteDTO.getEnderecoDTO()));

        return cliente;
    }
}
