package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.produto.ProdutoConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.produto.ProdutoDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Produto;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.EntradaProdutoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.FiltroListarProdutos;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.ProdutoDTO;
import com.gmail.gabrielcacarvalho.RestApi.repositories.ProdutoRepository;
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
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    private Converter<EntradaProdutoDTO, Produto> produtoConverter = new ProdutoConverter();

    private Converter<Produto, ProdutoDTO> produtoDTOConverter = new ProdutoDTOConverter();

    public Page<ProdutoDTO> obterProdutos(Pageable pageable, FiltroListarProdutos filtros) {
        return produtoDTOConverter.from(produtoRepository.findAll(criarFiltroBuscarLista(filtros), pageable));
    }

    public ProdutoDTO obterProduto(Integer idProduto) {
        return produtoDTOConverter.from(produtoRepository.findById(idProduto).get());
    }

    public ProdutoDTO criaProduto(EntradaProdutoDTO entradaProdutoDTO) {
        return produtoDTOConverter.from(produtoRepository.save(produtoConverter.from(entradaProdutoDTO)));
    }

    public ProdutoDTO alteraProduto(EntradaProdutoDTO entradaProdutoDTO) {
        return produtoDTOConverter.from(produtoRepository.save(produtoConverter.from(entradaProdutoDTO)));
    }

    private Specification<Produto> criarFiltroBuscarLista(FiltroListarProdutos filtroListarProdutos) {

        FiltroListarProdutos filtros = Optional.ofNullable(filtroListarProdutos).orElse(new FiltroListarProdutos());

        return (root, query, builder)->{
            List<Predicate> predicates = new ArrayList<>();

            if(filtros.getIdTipoVestimenta() != null)
                predicates.add(builder.equal(root.get("tipoVestimenta").get("id"), filtros.getIdTipoVestimenta()));

            if (filtros.getCategoria() != null)
                predicates.add(builder.equal(root.get("categoria"), filtros.getCategoria()));

            if (filtros.getCor() != null)
                predicates.add(builder.equal(root.get("cor"), filtros.getCor()));

            if (filtros.getIdMarca() != null)
                predicates.add(builder.equal(root.get("marca").get("id"), filtros.getIdMarca()));

            if (filtros.getIdPromocao() != null)
                predicates.add(builder.equal(root.get("promocao").get("id"), filtros.getIdPromocao()));

            if (filtros.getTamanho() != null)
                predicates.add(builder.equal(root.get("tamanho"), filtros.getTamanho()));

            if (filtros.getPrecoMin() != null && filtros.getPrecoMax() != null)
                predicates.add(builder.between(root.get("valorUnitario"), filtros.getPrecoMin(), filtros.getPrecoMax()));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
