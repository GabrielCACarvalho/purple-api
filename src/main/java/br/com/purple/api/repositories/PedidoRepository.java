package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Optional<Pedido> findByAbertoAndClienteCredencialClienteUsuario(Boolean aberto, String usuario);
}
