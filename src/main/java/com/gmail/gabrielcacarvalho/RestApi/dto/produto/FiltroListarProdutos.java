package com.gmail.gabrielcacarvalho.RestApi.dto.produto;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Tamanho;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Categoria;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Marca;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Promocao;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.TipoVestimenta;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FiltroListarProdutos {

    private Integer idTipoVestimenta;
    private Categoria categoria;
    private Integer idMarca;
    private Integer idPromocao;
    private String cor;
    private Tamanho tamanho;
    private BigDecimal precoMin;
    private BigDecimal precoMax;
}
