package com.gmail.gabrielcacarvalho.RestApi.core.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Categoria;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.type.BinaryType;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
    @OneToMany
    private List<Imagem> imagens;
    @ManyToOne
    private Promocao promocao;
    @ManyToOne
    private TipoVestimenta tipoVestimenta;
}
