package com.gmail.gabrielcacarvalho.RestApi.core.entity.model;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.enumerator.Sexo;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String nome;
    @Column(length = 14)
    private String cnpj;
    @Column(length = 11)
    private String cpf;
    @OneToOne
    private CredencialCliente credencialCliente;
    @Column(length = 11)
    private String celular;
    @OneToOne
    private Endereco endereco;
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
}
