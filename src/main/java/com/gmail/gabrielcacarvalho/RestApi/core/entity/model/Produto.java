package com.gmail.gabrielcacarvalho.RestApi.core.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Categoria;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
public class Produto implements Serializable {

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
    @ManyToOne
    private TipoVestimenta tipoVestimenta;
}
