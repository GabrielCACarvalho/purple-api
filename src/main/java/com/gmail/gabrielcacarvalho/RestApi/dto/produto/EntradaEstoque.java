package com.gmail.gabrielcacarvalho.RestApi.dto.produto;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Tamanho;
import lombok.Data;

@Data
public class EntradaEstoque {

    private Integer idProduto;
    private Integer quantidade;
    private Tamanho tamanho;
}
