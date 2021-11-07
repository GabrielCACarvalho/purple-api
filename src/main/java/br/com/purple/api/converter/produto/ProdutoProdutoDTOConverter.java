package br.com.purple.api.converter.produto;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.promocao.PromocaoPromocaoDTOConverter;
import br.com.purple.api.converter.tipovestimenta.TipoVestimentaTipoVestimentaDTOConverter;
import br.com.purple.api.core.entity.model.TipoVestimenta;
import br.com.purple.api.dto.enumerator.CategoriaDTO;
import br.com.purple.api.dto.marca.MarcaDTO;
import br.com.purple.api.dto.produto.ProdutoDTO;
import br.com.purple.api.dto.promocao.PromocaoDTO;
import br.com.purple.api.dto.tipovestimenta.TipoVestimentaDTO;
import br.com.purple.api.converter.marca.MarcaMarcaDTOConverter;
import br.com.purple.api.core.entity.model.Marca;
import br.com.purple.api.core.entity.model.Produto;
import br.com.purple.api.core.entity.model.Promocao;

public class ProdutoProdutoDTOConverter implements Converter<Produto, ProdutoDTO> {

    private Converter<Promocao, PromocaoDTO> promocaoDTOConverter = new PromocaoPromocaoDTOConverter();
    private Converter<Marca, MarcaDTO> marcaDTOConverter = new MarcaMarcaDTOConverter();
    private Converter<TipoVestimenta, TipoVestimentaDTO> tipoVestimentaDTOConverter = new TipoVestimentaTipoVestimentaDTOConverter();

    @Override
    public ProdutoDTO from(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setId(produto.getId());
        produtoDTO.setCor(produto.getCor());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setValorUnitario(produto.getValorUnitario());
        if (produto.getCategoria() != null)
            produtoDTO.setCategoria(CategoriaDTO.valueOf(produto.getCategoria().name()));
        if (produto.getPromocao() != null)
            produtoDTO.setPromocao(promocaoDTOConverter.from(produto.getPromocao()));
        if (produto.getMarca() != null)
            produtoDTO.setMarca(marcaDTOConverter.from(produto.getMarca()));
        if (produto.getTipoVestimenta() != null)
            produtoDTO.setTipoVestimenta(tipoVestimentaDTOConverter.from(produto.getTipoVestimenta()));

        return produtoDTO;
    }
}
