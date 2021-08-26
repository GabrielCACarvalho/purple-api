package com.gmail.gabrielcacarvalho.RestApi.repositories;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>, JpaSpecificationExecutor<Produto> {
}
