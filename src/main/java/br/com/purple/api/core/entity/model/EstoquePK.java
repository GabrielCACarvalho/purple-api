package br.com.purple.api.core.entity.model;

import br.com.purple.api.core.entity.enumerator.Tamanho;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EstoquePK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "idProduto", referencedColumnName = "id")
    private Produto produto;

    private Tamanho tamanho;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstoquePK estoquePK = (EstoquePK) o;
        return Objects.equals(produto, estoquePK.produto) && tamanho == estoquePK.tamanho;
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, tamanho);
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
}