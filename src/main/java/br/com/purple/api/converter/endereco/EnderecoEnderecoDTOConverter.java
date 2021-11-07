package br.com.purple.api.converter.endereco;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.dto.endereco.EnderecoDTO;
import br.com.purple.api.core.entity.model.Endereco;

public class EnderecoEnderecoDTOConverter implements Converter<Endereco, EnderecoDTO> {

    @Override
    public EnderecoDTO from(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setNumero(endereco.getNumero());

        return enderecoDTO;
    }
}
