package com.gmail.gabrielcacarvalho.RestApi.core.entity.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Estoque implements Serializable {

    @Id
    @OneToOne
    private Produto produto;
    private Integer quantidadeEmEstoque;
}
