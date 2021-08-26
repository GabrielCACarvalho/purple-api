package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Produto;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.FiltroListarProdutos;
import com.gmail.gabrielcacarvalho.RestApi.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<Produto> obterProdutos(Pageable pageable, FiltroListarProdutos filtros) {
        return produtoRepository.findAll(criarFiltroBuscarLista(filtros), pageable);
    }

    private Specification<Produto> criarFiltroBuscarLista(FiltroListarProdutos filtros) {
        return (root, query, builder)->{
            List<Predicate> predicates = new ArrayList<>();

            //if ()

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
