package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.estoque.EstoqueDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Estoque;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.EstoquePK;
import com.gmail.gabrielcacarvalho.RestApi.dto.estoque.EstoqueDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.estoque.FiltroConsultaEstoque;
import com.gmail.gabrielcacarvalho.RestApi.dto.estoque.SaidaEstoque;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.EntradaEstoque;
import com.gmail.gabrielcacarvalho.RestApi.repositories.EstoqueRepository;
import com.gmail.gabrielcacarvalho.RestApi.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    private Converter<Estoque, EstoqueDTO> estoqueDTOConverter = new EstoqueDTOConverter();

    public EstoqueDTO consultaEstoqueProduto(FiltroConsultaEstoque filtroConsultaEstoque) {

        return estoqueDTOConverter.from(Optional.ofNullable(estoqueRepository
                .getByIdProdutoIdAndIdTamanho(filtroConsultaEstoque.getIdProduto(),
                        filtroConsultaEstoque.getTamanho())).orElse(new Estoque()));
    }

    public EstoqueDTO entradaNoEstoque(EntradaEstoque entradaEstoque){
        Estoque estoque = obtemEstoqueAtual(entradaEstoque);

        estoque.setQuantidadeEmEstoque(estoque.getQuantidadeEmEstoque() + entradaEstoque.getQuantidade());

        return estoqueDTOConverter.from(estoqueRepository.save(estoque));
    }

    public EstoqueDTO saidaNoEstoque(SaidaEstoque saidaEstoque) {
        Estoque estoque = estoqueRepository.getByIdProdutoIdAndIdTamanho(saidaEstoque.getIdProduto(), saidaEstoque.getTamanho());

        if (estoque == null)
            throw new RuntimeException("ESSE PRODUTO NAO TEM ESTOQUE"); //TODO: Fazer validação com java validation

        estoque.setQuantidadeEmEstoque(estoque.getQuantidadeEmEstoque() - saidaEstoque.getQuantidade());

        return estoqueDTOConverter.from(estoqueRepository.save(estoque));
    }

    public Estoque obtemEstoqueAtual(EntradaEstoque entradaEstoque){
        Estoque estoque = estoqueRepository.getByIdProdutoIdAndIdTamanho(entradaEstoque.getIdProduto(), entradaEstoque.getTamanho());

        if (estoque != null) {
            return estoque;
        }

        return criaNovoEstoque(entradaEstoque);
    }

    private Estoque criaNovoEstoque(EntradaEstoque entradaEstoque) {
        Estoque estoque = new Estoque();

        estoque.setId(criaEstoquePK(entradaEstoque));
        estoque.setQuantidadeEmEstoque(0);

        return estoque;
    }

    private EstoquePK criaEstoquePK(EntradaEstoque entradaEstoque) {
        EstoquePK estoquePK = new EstoquePK();

        estoquePK.setProduto(produtoRepository.getById(entradaEstoque.getIdProduto()));
        estoquePK.setTamanho(entradaEstoque.getTamanho());

        return estoquePK;
    }
}
