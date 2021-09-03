package com.gmail.gabrielcacarvalho.RestApi.converter.endereco;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Endereco;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.EnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.EntradaEnderecoDTO;

public class EnderecoConverter implements Converter<EntradaEnderecoDTO, Endereco> {

    @Override
    public Endereco from(EntradaEnderecoDTO entradaEnderecoDTO) {
        Endereco endereco = new Endereco();

        endereco.setBairro(entradaEnderecoDTO.getBairro());
        endereco.setCep(entradaEnderecoDTO.getCep());
        endereco.setCidade(entradaEnderecoDTO.getCidade());
        endereco.setEstado(entradaEnderecoDTO.getEstado());
        endereco.setNumero(entradaEnderecoDTO.getNumero());
        endereco.setRua(entradaEnderecoDTO.getRua());

        return endereco;
    }
}
