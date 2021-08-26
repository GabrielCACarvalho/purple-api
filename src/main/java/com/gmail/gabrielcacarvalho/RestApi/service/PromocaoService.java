package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Promocao;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.FiltroListarPromocoes;
import com.gmail.gabrielcacarvalho.RestApi.repositories.PromocaoRepository;
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
public class PromocaoService {

    @Autowired
    private PromocaoRepository promocaoRepository;

    public Page<Promocao> obterPromocoes(Pageable pageable, FiltroListarPromocoes filtros){
        return promocaoRepository.findAll(criarFiltrosBuscarLista(filtros), pageable);
    }

    private Specification<Promocao> criarFiltrosBuscarLista(FiltroListarPromocoes filtroListarPromocoes){

        FiltroListarPromocoes filtros = Optional.ofNullable(filtroListarPromocoes).orElse(new FiltroListarPromocoes());

        return (root, query, builder) ->{
            List<Predicate> predicates = new ArrayList<>();

            if (filtros.getDataFim() != null && filtros.getDataInicio() != null)
                predicates.add(builder.between(root.get("dataInicio"),
                        filtros.getDataInicio(),
                        filtros.getDataFim()));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
