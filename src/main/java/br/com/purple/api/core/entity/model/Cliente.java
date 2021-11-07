package br.com.purple.api.core.entity.model;

import br.com.purple.api.core.entity.enumerator.Sexo;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String nome;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public CredencialCliente getCredencialCliente() {
        return credencialCliente;
    }

    public void setCredencialCliente(CredencialCliente credencialCliente) {
        this.credencialCliente = credencialCliente;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
