package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
