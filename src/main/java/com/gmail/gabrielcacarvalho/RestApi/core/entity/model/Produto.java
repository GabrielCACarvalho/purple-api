package com.gmail.gabrielcacarvalho.RestApi.core.entity.model;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Categoria;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    @ManyToOne
    private Marca marca;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private BigDecimal valorUnitario;
    private String cor;
    @ManyToOne
    private Promocao promocao;
}
