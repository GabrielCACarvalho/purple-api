package com.gmail.gabrielcacarvalho.RestApi.converter.endereco;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Endereco;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.AlteraEnderecoDTO;

public class AlteraDTOEnderecoConverter implements Converter<AlteraEnderecoDTO, Endereco> {

    @Override
    public Endereco from(AlteraEnderecoDTO alteraEnderecoDTO) {
        Endereco endereco = new Endereco();

        endereco.setCep(alteraEnderecoDTO.getCep());
        endereco.setRua(alteraEnderecoDTO.getRua());
        endereco.setNumero(alteraEnderecoDTO.getNumero());
        endereco.setEstado(alteraEnderecoDTO.getEstado());
        endereco.setCidade(alteraEnderecoDTO.getCidade());
        endereco.setBairro(alteraEnderecoDTO.getBairro());

        return endereco;
    }
}
