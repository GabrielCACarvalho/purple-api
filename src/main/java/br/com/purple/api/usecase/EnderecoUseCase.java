package br.com.purple.api.usecase;

import br.com.purple.api.config.security.util.ClienteAutenticadoUtil;
import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.endereco.AlteraDTOEnderecoConverter;
import br.com.purple.api.converter.endereco.EnderecoEnderecoDTOConverter;
import br.com.purple.api.converter.endereco.EntradaDTOEnderecoConverter;
import br.com.purple.api.core.entity.model.Cliente;
import br.com.purple.api.core.entity.model.Endereco;
import br.com.purple.api.dto.endereco.AlteraEnderecoDTO;
import br.com.purple.api.dto.endereco.EnderecoDTO;
import br.com.purple.api.dto.endereco.EntradaEnderecoDTO;
import br.com.purple.api.repositories.EnderecoRepository;
import br.com.purple.api.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoUseCase {

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
