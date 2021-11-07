package br.com.purple.api.core.entity.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
