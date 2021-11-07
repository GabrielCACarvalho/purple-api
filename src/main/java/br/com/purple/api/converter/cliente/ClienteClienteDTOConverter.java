package br.com.purple.api.converter.cliente;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.endereco.EnderecoEnderecoDTOConverter;
import br.com.purple.api.core.entity.model.Cliente;
import br.com.purple.api.dto.cliente.ClienteDTO;

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
