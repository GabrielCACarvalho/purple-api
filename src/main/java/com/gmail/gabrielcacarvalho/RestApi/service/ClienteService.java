package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Cliente;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.FiltroListarClientes;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.FiltroListarPromocoes;
import com.gmail.gabrielcacarvalho.RestApi.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> obterClientes(Pageable pageable, FiltroListarClientes filtros){
        return clienteRepository.findAll(criarFiltrosBuscarLista(filtros), pageable);
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
}
