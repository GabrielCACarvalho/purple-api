package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.CredencialCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredencialClienteRepository extends JpaRepository<CredencialCliente, String> {
    CredencialCliente findByUsuario(String usuario);
}
