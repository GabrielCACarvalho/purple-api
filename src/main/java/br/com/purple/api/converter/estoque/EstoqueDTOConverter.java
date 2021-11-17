package br.com.purple.api.converter.estoque;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.produto.ProdutoProdutoDTOConverter;
import br.com.purple.api.dto.enumerator.TamanhoDTO;
import br.com.purple.api.dto.estoque.EstoqueDTO;
import br.com.purple.api.dto.produto.ProdutoDTO;
import br.com.purple.api.core.entity.model.Estoque;
import br.com.purple.api.core.entity.model.Produto;

public class EstoqueDTOConverter implements Converter<Estoque, EstoqueDTO> {

    @Override
    public EstoqueDTO from(Estoque estoque) {
        EstoqueDTO estoqueDTO = new EstoqueDTO();

        if (estoque.getId() != null) {
            estoqueDTO.setIdProduto(estoque.getId().getProduto().getId());
            estoqueDTO.setTamanho(TamanhoDTO.valueOf(estoque.getId().getTamanho().name()));
        }
        estoqueDTO.setQuantidadeEmEstoque(estoque.getQuantidadeEmEstoque());

        return estoqueDTO;
    }
}
