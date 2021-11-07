package br.com.purple.api.converter.cliente;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.dto.cliente.EntradaClienteDTO;
import br.com.purple.api.config.security.util.ClienteAutenticadoUtil;
import br.com.purple.api.converter.endereco.EntradaDTOEnderecoConverter;
import br.com.purple.api.core.entity.model.Cliente;
import br.com.purple.api.core.entity.model.CredencialCliente;

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
