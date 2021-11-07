package br.com.purple.api.converter.produto;

import br.com.purple.api.core.entity.model.TipoVestimenta;
import br.com.purple.api.dto.produto.AlteraProdutoDTO;
import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.enumerator.Categoria;
import br.com.purple.api.core.entity.model.Marca;
import br.com.purple.api.core.entity.model.Produto;
import br.com.purple.api.core.entity.model.Promocao;

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
