package com.gmail.gabrielcacarvalho.RestApi.dto.produto;

import com.gmail.gabrielcacarvalho.RestApi.dto.enumerator.CategoriaDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.marca.MarcaDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.PromocaoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.tipovestimenta.TipoVestimentaDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private MarcaDTO marca;
    private CategoriaDTO categoria;
    private BigDecimal valorUnitario;
    private String cor;
    private PromocaoDTO promocao;
    private TipoVestimentaDTO tipoVestimenta;
}
