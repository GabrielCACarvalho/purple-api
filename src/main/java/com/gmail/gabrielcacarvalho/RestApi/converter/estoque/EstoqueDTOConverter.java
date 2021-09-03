package com.gmail.gabrielcacarvalho.RestApi.converter.estoque;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.produto.ProdutoDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Estoque;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Produto;
import com.gmail.gabrielcacarvalho.RestApi.dto.enumerator.TamanhoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.estoque.EstoqueDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.ProdutoDTO;

public class EstoqueDTOConverter implements Converter<Estoque, EstoqueDTO> {

    private Converter<Produto, ProdutoDTO> produtoDTOConverter = new ProdutoDTOConverter();

    @Override
    public EstoqueDTO from(Estoque estoque) {
        EstoqueDTO estoqueDTO = new EstoqueDTO();

        estoqueDTO.setProduto(produtoDTOConverter.from(estoque.getId().getProduto()));
        estoqueDTO.setTamanho(TamanhoDTO.valueOf(estoque.getId().getTamanho().name()));
        estoqueDTO.setQuantidadeEmEstoque(estoque.getQuantidadeEmEstoque());

        return estoqueDTO;
    }
}
