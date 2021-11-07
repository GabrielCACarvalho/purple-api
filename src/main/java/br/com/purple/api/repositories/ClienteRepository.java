package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {
    Cliente findByCredencialClienteUsuario(String usuario);
}
