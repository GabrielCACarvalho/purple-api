package com.gmail.gabrielcacarvalho.RestApi.core.entity.model;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Tamanho;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Produto produto;
    private Integer quantidade;
    private Tamanho tamanho;
    private BigDecimal valorTotal;
}
