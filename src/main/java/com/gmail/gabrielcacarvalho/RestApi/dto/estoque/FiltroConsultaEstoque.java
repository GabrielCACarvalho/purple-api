package com.gmail.gabrielcacarvalho.RestApi.dto.estoque;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Tamanho;
import lombok.Data;

@Data
public class FiltroConsultaEstoque {

    private Integer idProduto;
    private Tamanho tamanho;
}
