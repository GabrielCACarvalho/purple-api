package br.com.purple.api.dto.estoque;

import br.com.purple.api.dto.produto.ProdutoDTO;
import br.com.purple.api.dto.enumerator.TamanhoDTO;
import lombok.Data;

@Data
public class EstoqueDTO {

    private ProdutoDTO produto;
    private TamanhoDTO tamanho;
    private Integer quantidadeEmEstoque;
}
