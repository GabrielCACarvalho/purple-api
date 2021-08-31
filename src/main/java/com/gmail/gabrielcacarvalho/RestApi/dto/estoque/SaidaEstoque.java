package com.gmail.gabrielcacarvalho.RestApi.dto.estoque;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Tamanho;
import lombok.Data;

@Data
public class SaidaEstoque {

    private Integer idProduto;
    private Integer quantidade;
    private Tamanho tamanho;
}
