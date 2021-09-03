package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.endereco.EnderecoDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.endereco.EnderecoConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Endereco;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.EnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.EntradaEnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Converter<EntradaEnderecoDTO, Endereco> enderecoConverter = new EnderecoConverter();

    private Converter<Endereco, EnderecoDTO> enderecoDTOConverter = new EnderecoDTOConverter();

    public EnderecoDTO criaEndereco(EntradaEnderecoDTO entradaEnderecoDTO) {
        return enderecoDTOConverter.from(enderecoRepository.save(enderecoConverter.from(entradaEnderecoDTO)));
    }

    public EnderecoDTO alteraEndereco(EntradaEnderecoDTO entradaEnderecoDTO) {
        return enderecoDTOConverter.from(enderecoRepository.save(enderecoConverter.from(entradaEnderecoDTO)));
    }
}
