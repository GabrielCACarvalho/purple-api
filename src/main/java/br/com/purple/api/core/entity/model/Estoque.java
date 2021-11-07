package br.com.purple.api.core.entity.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Estoque implements Serializable {

    @EmbeddedId
    private EstoquePK id;
    private Integer quantidadeEmEstoque;

    public EstoquePK getId() {
        return id;
    }

    public void setId(EstoquePK id) {
        this.id = id;
    }

    public Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
}
