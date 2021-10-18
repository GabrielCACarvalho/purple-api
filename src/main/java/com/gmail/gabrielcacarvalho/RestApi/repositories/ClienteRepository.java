package com.gmail.gabrielcacarvalho.RestApi.repositories;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {
    Cliente findByCredencialClienteUsuario(String usuario);
}
