package com.gmail.gabrielcacarvalho.RestApi.repositories;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.CredencialCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredencialClienteRepository extends JpaRepository<CredencialCliente, String> {
    CredencialCliente findByUsuario(String usuario);
}
