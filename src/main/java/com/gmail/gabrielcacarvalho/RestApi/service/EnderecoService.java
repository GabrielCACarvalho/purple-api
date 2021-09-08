package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.endereco.AlteraDTOEnderecoConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.endereco.EnderecoEnderecoDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.endereco.EntradaDTOEnderecoConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Endereco;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.AlteraEnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.EnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.EntradaEnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Converter<EntradaEnderecoDTO, Endereco> entradaDTOEnderecoConverter = new EntradaDTOEnderecoConverter();

    private Converter<Endereco, EnderecoDTO> enderecoEnderecoDTOConverter = new EnderecoEnderecoDTOConverter();

    private Converter<AlteraEnderecoDTO, Endereco> alteraDTOEnderecoConverter = new AlteraDTOEnderecoConverter();

    public EnderecoDTO criaEndereco(EntradaEnderecoDTO entradaEnderecoDTO) {
        return enderecoEnderecoDTOConverter.from(enderecoRepository.save(entradaDTOEnderecoConverter.from(entradaEnderecoDTO)));
    }

    public EnderecoDTO alteraEndereco(AlteraEnderecoDTO alteraEnderecoDTO) {
        return enderecoEnderecoDTOConverter.from(enderecoRepository.save(alteraDTOEnderecoConverter.from(alteraEnderecoDTO)));
    }

    public EnderecoDTO obterEndereco(Integer idEndereco) {
        return enderecoEnderecoDTOConverter.from(enderecoRepository.findById(idEndereco).get());
    }
}
