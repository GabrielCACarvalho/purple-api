package com.gmail.gabrielcacarvalho.RestApi.core.entity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String estado;
    private String cidade;
    private String bairro;
    @Column(length = 8)
    private String cep;
    private String rua;
    private String numero;
}
