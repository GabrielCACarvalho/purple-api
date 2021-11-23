package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>, JpaSpecificationExecutor<Pedido> {
    Optional<Pedido> findByAbertoAndClienteCredencialClienteUsuario(Boolean aberto, String usuario);
}
