package br.com.purple.api.converter.produto;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.TipoVestimenta;
import br.com.purple.api.core.entity.enumerator.Categoria;
import br.com.purple.api.core.entity.model.Marca;
import br.com.purple.api.core.entity.model.Produto;
import br.com.purple.api.core.entity.model.Promocao;
import br.com.purple.api.dto.produto.EntradaProdutoDTO;
import br.com.purple.api.usecase.CredencialClienteUseCase;

public class EntradaDTOProdutoConverter implements Converter<EntradaProdutoDTO, Produto> {

    @Override
    public Produto from(EntradaProdutoDTO entradaProdutoDTO) {
        Produto produto = new Produto();

        produto.setDescricao(entradaProdutoDTO.getDescricao());
        produto.setCor(entradaProdutoDTO.getCor());
        produto.setPath(CredencialClienteUseCase.passwordEncoder().encode(entradaProdutoDTO.getNome()));
        produto.setNome(entradaProdutoDTO.getNome());
        produto.setValorUnitario(entradaProdutoDTO.getValorUnitario());
        produto.setCategoria(Categoria.valueOf(entradaProdutoDTO.getCategoria().name()));
        if (entradaProdutoDTO.getIdMarca() != null)
            produto.setMarca(new Marca(entradaProdutoDTO.getIdMarca()));
        if (entradaProdutoDTO.getIdPromocao() != null)
            produto.setPromocao(new Promocao(entradaProdutoDTO.getIdPromocao()));
        if (entradaProdutoDTO.getIdTipoVestimenta() != null)
            produto.setTipoVestimenta(new TipoVestimenta(entradaProdutoDTO.getIdTipoVestimenta()));

        return produto;
    }
}
