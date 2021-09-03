package com.gmail.gabrielcacarvalho.RestApi.dto.estoque;

import com.gmail.gabrielcacarvalho.RestApi.dto.enumerator.TamanhoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.ProdutoDTO;
import lombok.Data;

@Data
public class EstoqueDTO {

    private ProdutoDTO produto;
    private TamanhoDTO tamanho;
    private Integer quantidadeEmEstoque;
}
