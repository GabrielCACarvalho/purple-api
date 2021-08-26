package com.gmail.gabrielcacarvalho.RestApi.core.entity.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;
    private String nome;
    private String descricao;
    @ManyToOne
    private Marca marca;
    @ManyToOne
    private Categoria categoria;
    private BigDecimal valorUnitario;
    private String cor;
    @ManyToOne
    private Promocao promocao;
}
