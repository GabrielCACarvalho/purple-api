package com.gmail.gabrielcacarvalho.RestApi.repositories;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Tamanho;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Estoque;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.EstoquePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository <Estoque, EstoquePK> {
    Estoque getByIdProdutoIdAndIdTamanho(Integer idProduto, Tamanho tamanho);
}
