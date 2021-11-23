package br.com.purple.api.usecase;

import br.com.purple.api.config.security.util.ClienteAutenticadoUtil;
import br.com.purple.api.converter.cliente.ClienteClienteDTOConverter;
import br.com.purple.api.core.entity.model.Cliente;
import br.com.purple.api.core.entity.model.CredencialCliente;
import br.com.purple.api.dto.cliente.AlteraClienteDTO;
import br.com.purple.api.dto.cliente.ClienteDTO;
import br.com.purple.api.dto.cliente.FiltroListarClientes;
import br.com.purple.api.dto.cliente.FiltroTotalClientes;
import br.com.purple.api.repositories.ClienteRepository;
import br.com.purple.api.repositories.CredencialClienteRepository;
import br.com.purple.api.repositories.EnderecoRepository;
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
public class ClienteUseCase {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CredencialClienteRepository credencialClienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    private ClienteClienteDTOConverter clienteClienteDTOConverter = new ClienteClienteDTOConverter();

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

    public Long obterTotalClientes(FiltroTotalClientes filtro) {
        return clienteRepository.count(criarFiltroContagem(filtro));
    }

    private Specification<Cliente> criarFiltroContagem(FiltroTotalClientes filtro) {
        FiltroTotalClientes filtros = Optional.ofNullable(filtro).orElse(new FiltroTotalClientes());

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtros.getDataFim() != null && filtros.getDataInicio() != null)
                predicates.add(builder.between(root.get("dataCadastro"),
                        java.sql.Date.valueOf(filtros.getDataInicio()),
                        java.sql.Date.valueOf(filtros.getDataFim())));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
