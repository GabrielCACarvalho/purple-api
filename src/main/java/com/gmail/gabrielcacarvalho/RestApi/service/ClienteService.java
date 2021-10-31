package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.config.security.util.ClienteAutenticadoUtil;
import com.gmail.gabrielcacarvalho.RestApi.converter.cliente.AlteraDTOClienteConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.cliente.ClienteClienteDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.cliente.EntradaDTOClienteConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.endereco.EntradaDTOEnderecoConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Cliente;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.CredencialCliente;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.AlteraClienteDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.ClienteDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.EntradaClienteDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.FiltroListarClientes;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.EntradaEnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.repositories.ClienteRepository;
import com.gmail.gabrielcacarvalho.RestApi.repositories.CredencialClienteRepository;
import com.gmail.gabrielcacarvalho.RestApi.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CredencialClienteRepository credencialClienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    private EntradaDTOClienteConverter entradaDTOClienteConverter = new EntradaDTOClienteConverter();
    private ClienteClienteDTOConverter clienteClienteDTOConverter = new ClienteClienteDTOConverter();
    private EntradaDTOEnderecoConverter entradaDTOEnderecoConverter = new EntradaDTOEnderecoConverter();
    private AlteraDTOClienteConverter alteraDTOClienteConverter = new AlteraDTOClienteConverter();

    public Page<ClienteDTO> obterClientes(Pageable pageable, FiltroListarClientes filtros){
        return clienteClienteDTOConverter.from(clienteRepository.findAll(criarFiltrosBuscarLista(filtros), pageable));
    }

    public ClienteDTO novoCliente(){
        Cliente cliente = new Cliente();

        cliente.setCredencialCliente(new CredencialCliente());
        cliente.getCredencialCliente().setUsuario(ClienteAutenticadoUtil.getUsuarioClienteAutenticado());

        cliente.setCredencialCliente(credencialClienteRepository.findByUsuario(cliente.getCredencialCliente().getUsuario()));
        cliente.setDataCadastro(Date.from(Instant.now()));

        return clienteClienteDTOConverter.from(clienteRepository.save(cliente));
    }

    public ClienteDTO alteraCliente(AlteraClienteDTO alteraClienteDTO){
        Cliente cliente = clienteRepository.findById(alteraClienteDTO.getId()).get();

        cliente.setNome(alteraClienteDTO.getNome());
        cliente.setCpf(alteraClienteDTO.getCpf());
        cliente.setCelular(alteraClienteDTO.getCelular());
        cliente.setSexo(alteraClienteDTO.getSexo());

        return clienteClienteDTOConverter.from(clienteRepository.save(cliente));
    }

    private Specification<Cliente> criarFiltrosBuscarLista(FiltroListarClientes filtroListarClientes){

        FiltroListarClientes filtros = Optional.ofNullable(filtroListarClientes).orElse(new FiltroListarClientes());

        return (root, query, builder) ->{
            List<Predicate> predicates = new ArrayList<>();

            if (filtros.getId() != null)
                predicates.add(builder.equal(root.get("id"),
                        filtros.getId()));
            if(filtros.getSexo() != null)
                predicates.add(builder.equal(root.get("sexo"),
                        filtros.getSexo()));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Cliente consultaClienteAutenticado() {
       return clienteRepository.findByCredencialClienteUsuario(ClienteAutenticadoUtil.getUsuarioClienteAutenticado());
    }
}
