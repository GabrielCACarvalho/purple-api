package com.gmail.gabrielcacarvalho.RestApi.converter.produto;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Categoria;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Marca;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Produto;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Promocao;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.TipoVestimenta;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.AlteraProdutoDTO;

public class AlteraDTOProdutoConverter implements Converter<AlteraProdutoDTO, Produto> {

    @Override
    public Produto from(AlteraProdutoDTO alteraProdutoDTO) {
        Produto produto = new Produto();

        produto.setId(alteraProdutoDTO.getId());
        produto.setCor(alteraProdutoDTO.getCor());
        produto.setNome(alteraProdutoDTO.getNome());
        produto.setDescricao(alteraProdutoDTO.getDescricao());
        produto.setValorUnitario(alteraProdutoDTO.getValorUnitario());
        produto.setCategoria(Categoria.valueOf(alteraProdutoDTO.getCategoria().name()));
        produto.setTipoVestimenta(new TipoVestimenta(alteraProdutoDTO.getIdTipoVestimenta()));
        produto.setMarca(new Marca(alteraProdutoDTO.getIdMarca()));
        produto.setPromocao(new Promocao(alteraProdutoDTO.getIdPromocao()));

        return produto;
    }
}
