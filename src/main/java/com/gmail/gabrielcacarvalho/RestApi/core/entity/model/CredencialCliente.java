package com.gmail.gabrielcacarvalho.RestApi.core.entity.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class CredencialCliente {

    @Id
    private String usuario;
    private String senha;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
