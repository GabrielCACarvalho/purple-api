package br.com.purple.api.core.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.purple.api.core.entity.enumerator.Categoria;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column(length = 5000)
    private String descricao;
    @ManyToOne
    private Marca marca;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private BigDecimal valorUnitario;
    private String path;
    private String cor;
    @OneToMany
    private List<Imagem> imagens;
    @ManyToOne
    private Promocao promocao;
    @ManyToOne
    private TipoVestimenta tipoVestimenta;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public TipoVestimenta getTipoVestimenta() {
        return tipoVestimenta;
    }

    public void setTipoVestimenta(TipoVestimenta tipoVestimenta) {
        this.tipoVestimenta = tipoVestimenta;
    }
}
