package com.gmail.gabrielcacarvalho.RestApi.dto.produto;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Tamanho;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Categoria;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Marca;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Promocao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FiltroListarProdutos {

    //private TipoVestimenta tipo;
    private Categoria categoria;
    private Marca marca;
    private Promocao promocao;
    private String cor;
    private Tamanho tamanho;
    private BigDecimal precoMin;
    private BigDecimal precoMax;
}
