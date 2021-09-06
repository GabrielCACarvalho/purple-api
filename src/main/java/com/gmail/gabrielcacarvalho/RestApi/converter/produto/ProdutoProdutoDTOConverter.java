package com.gmail.gabrielcacarvalho.RestApi.converter.produto;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.marca.MarcaDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.promocao.PromocaoPromocaoDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.tipovestimenta.TipoVestimentaDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Marca;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Produto;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Promocao;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.TipoVestimenta;
import com.gmail.gabrielcacarvalho.RestApi.dto.enumerator.CategoriaDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.marca.MarcaDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.ProdutoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.PromocaoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.tipovestimenta.TipoVestimentaDTO;

public class ProdutoProdutoDTOConverter implements Converter<Produto, ProdutoDTO> {

    private Converter<Promocao, PromocaoDTO> promocaoDTOConverter = new PromocaoPromocaoDTOConverter();
    private Converter<Marca, MarcaDTO> marcaDTOConverter = new MarcaDTOConverter();
    private Converter<TipoVestimenta, TipoVestimentaDTO> tipoVestimentaDTOConverter = new TipoVestimentaDTOConverter();

    @Override
    public ProdutoDTO from(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setId(produto.getId());
        produtoDTO.setCor(produto.getCor());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setValorUnitario(produto.getValorUnitario());
        produtoDTO.setCategoria(CategoriaDTO.valueOf(produto.getCategoria().name()));
        produtoDTO.setPromocao(promocaoDTOConverter.from(produto.getPromocao()));
        produtoDTO.setMarca(marcaDTOConverter.from(produto.getMarca()));
        produtoDTO.setTipoVestimenta(tipoVestimentaDTOConverter.from(produto.getTipoVestimenta()));

        return produtoDTO;
    }
}
