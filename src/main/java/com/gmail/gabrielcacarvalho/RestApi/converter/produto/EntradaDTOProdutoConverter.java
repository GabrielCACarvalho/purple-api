package com.gmail.gabrielcacarvalho.RestApi.converter.produto;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Categoria;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Marca;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Produto;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Promocao;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.TipoVestimenta;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.EntradaProdutoDTO;

public class EntradaDTOProdutoConverter implements Converter<EntradaProdutoDTO, Produto> {

    @Override
    public Produto from(EntradaProdutoDTO entradaProdutoDTO) {
        Produto produto = new Produto();

        produto.setDescricao(entradaProdutoDTO.getDescricao());
        produto.setCor(entradaProdutoDTO.getCor());
        produto.setNome(entradaProdutoDTO.getNome());
        produto.setValorUnitario(entradaProdutoDTO.getValorUnitario());
        produto.setCategoria(Categoria.valueOf(entradaProdutoDTO.getCategoria().name()));
        produto.setMarca(new Marca(entradaProdutoDTO.getIdMarca()));
        produto.setPromocao(new Promocao(entradaProdutoDTO.getIdPromocao()));
        produto.setTipoVestimenta(new TipoVestimenta(entradaProdutoDTO.getIdTipoVestimenta()));

        return produto;
    }
}
