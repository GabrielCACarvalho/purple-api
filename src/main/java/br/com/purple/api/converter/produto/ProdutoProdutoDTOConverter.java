package br.com.purple.api.converter.produto;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.promocao.PromocaoPromocaoDTOConverter;
import br.com.purple.api.converter.tipovestimenta.TipoVestimentaTipoVestimentaDTOConverter;
import br.com.purple.api.core.entity.model.*;
import br.com.purple.api.dto.enumerator.CategoriaDTO;
import br.com.purple.api.dto.marca.MarcaDTO;
import br.com.purple.api.dto.produto.ImagemDTO;
import br.com.purple.api.dto.produto.ProdutoDTO;
import br.com.purple.api.dto.promocao.PromocaoDTO;
import br.com.purple.api.dto.tipovestimenta.TipoVestimentaDTO;
import br.com.purple.api.converter.marca.MarcaMarcaDTOConverter;

public class ProdutoProdutoDTOConverter implements Converter<Produto, ProdutoDTO> {

    private Converter<Imagem, ImagemDTO> imagemImagemDTOConverter = new ImagemImagemDTOConverter();

    @Override
    public ProdutoDTO from(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setId(produto.getId());
        produtoDTO.setCor(produto.getCor());
        produtoDTO.setPath(produto.getPath());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setValorUnitario(produto.getValorUnitario());

        if (produto.getImagens() != null)
            if(produto.getImagens().size() > 0)
                produtoDTO.setImage(imagemImagemDTOConverter.from(produto.getImagens().get(1)));
        if (produto.getCategoria() != null)
            produtoDTO.setCategoria(CategoriaDTO.valueOf(produto.getCategoria().name()));
        if (produto.getPromocao() != null)
            produtoDTO.setIdPromocao(produto.getPromocao().getId());
        if (produto.getMarca() != null)
            produtoDTO.setIdMarca(produto.getMarca().getId());
        if (produto.getTipoVestimenta() != null)
            produtoDTO.setIdTipoVestimenta(produto.getTipoVestimenta().getId());

        return produtoDTO;
    }
}
