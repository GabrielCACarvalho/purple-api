package com.gmail.gabrielcacarvalho.RestApi.core.entity.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Imagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    @Lob
    private byte[] arquivo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProduto", referencedColumnName = "id")
    private Produto produto;
}
