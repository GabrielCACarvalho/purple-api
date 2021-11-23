package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Pedido;
import br.com.purple.api.dto.pedido.FiltroRendaTotal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>, JpaSpecificationExecutor<Pedido> {
    Optional<Pedido> findByAbertoAndClienteCredencialClienteUsuario(Boolean aberto, String usuario);

    Long countByAbertoAndDataCriacaoBetween(boolean aberto, Date dataInicio, Date dataFim);

    Long countByAberto(boolean aberto);

    @Query("SELECT SUM(p.valorTotal) FROM Pedido p WHERE p.aberto = false")
    Double selectSomatoriaValorTotalPedidos();

    @Query("SELECT SUM(p.valorTotal) FROM Pedido p WHERE p.aberto = false AND p.dataCriacao BETWEEN :#{#dataInicio} AND :#{#dataFim}")
    Double selectSomatoriaValorTotalPedidos(Date dataInicio, Date dataFim);
}
