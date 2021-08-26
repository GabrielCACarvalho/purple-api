package com.gmail.gabrielcacarvalho.RestApi.core.entity.model;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.StatusPedido;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private StatusPedido status;
    @ManyToOne
    private Cliente cliente;
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    @Temporal(TemporalType.DATE)
    private Date dataEnvio;
    private String formaPagamento;
    @OneToMany
    private List<Item> itens;
    private BigDecimal valorTotal;
}
