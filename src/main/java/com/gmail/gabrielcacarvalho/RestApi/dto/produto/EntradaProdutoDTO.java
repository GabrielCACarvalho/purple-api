package com.gmail.gabrielcacarvalho.RestApi.dto.produto;

import com.gmail.gabrielcacarvalho.RestApi.dto.enumerator.CategoriaDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EntradaProdutoDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private Integer idMarca;
    private CategoriaDTO categoria;
    private BigDecimal valorUnitario;
    private String cor;
    private Integer idPromocao;
    private Integer idTipoVestimenta;
}
