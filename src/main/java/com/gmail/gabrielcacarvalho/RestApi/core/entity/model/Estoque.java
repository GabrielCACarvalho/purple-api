package com.gmail.gabrielcacarvalho.RestApi.core.entity.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
public class Estoque implements Serializable {

    @EmbeddedId
    private EstoquePK id;
    private Integer quantidadeEmEstoque;
}
