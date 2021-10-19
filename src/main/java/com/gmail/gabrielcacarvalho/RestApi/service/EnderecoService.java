package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.config.security.util.ClienteAutenticadoUtil;
import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.endereco.AlteraDTOEnderecoConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.endereco.EnderecoEnderecoDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.endereco.EntradaDTOEnderecoConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Cliente;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Endereco;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.AlteraEnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.EnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.EntradaEnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.repositories.ClienteRepository;
import com.gmail.gabrielcacarvalho.RestApi.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Converter<EntradaEnderecoDTO, Endereco> entradaDTOEnderecoConverter = new EntradaDTOEnderecoConverter();

    private Converter<Endereco, EnderecoDTO> enderecoEnderecoDTOConverter = new EnderecoEnderecoDTOConverter();

    private Converter<AlteraEnderecoDTO, Endereco> alteraDTOEnderecoConverter = new AlteraDTOEnderecoConverter();

    public EnderecoDTO criaEndereco(EntradaEnderecoDTO entradaEnderecoDTO) {
        Endereco endereco = enderecoRepository.save(entradaDTOEnderecoConverter.from(entradaEnderecoDTO));

        Cliente cliente = clienteRepository.findByCredencialClienteUsuario(ClienteAutenticadoUtil.getUsuarioClienteAutenticado());

        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);

        return enderecoEnderecoDTOConverter.from(endereco);
    }

    public EnderecoDTO alteraEndereco(AlteraEnderecoDTO alteraEnderecoDTO) {
        Endereco endereco = alteraDTOEnderecoConverter.from(alteraEnderecoDTO);

        Cliente cliente = clienteRepository.findByCredencialClienteUsuario(ClienteAutenticadoUtil.getUsuarioClienteAutenticado());

        endereco.setId(cliente.getEndereco().getId());

        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);

        return enderecoEnderecoDTOConverter.from(enderecoRepository.save(endereco));
    }
}
