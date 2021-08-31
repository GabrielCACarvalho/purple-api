package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Estoque;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.EstoquePK;
import com.gmail.gabrielcacarvalho.RestApi.dto.estoque.FiltroConsultaEstoque;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.EntradaEstoque;
import com.gmail.gabrielcacarvalho.RestApi.repositories.EstoqueRepository;
import com.gmail.gabrielcacarvalho.RestApi.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Estoque consultaEstoqueProduto(FiltroConsultaEstoque filtroConsultaEstoque) {
        return estoqueRepository.getByIdProdutoIdAndIdTamanho(filtroConsultaEstoque.getIdProduto(), filtroConsultaEstoque.getTamanho());
    }

    public Estoque entradaNoEstoque(EntradaEstoque entradaEstoque){
        Estoque estoque = new Estoque();

        estoque.setId(criaEstoquePK(entradaEstoque));
        estoque.setQuantidadeEmEstoque(entradaEstoque.getQuantidade());

        return estoqueRepository.save(estoque);
    }

    private EstoquePK criaEstoquePK(EntradaEstoque entradaEstoque) {
        EstoquePK estoquePK = new EstoquePK();

        estoquePK.setProduto(produtoRepository.getById(entradaEstoque.getIdProduto()));
        estoquePK.setTamanho(entradaEstoque.getTamanho());

        return estoquePK;
    }
}
