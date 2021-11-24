package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Produto;
import br.com.purple.api.core.entity.model.TipoVestimenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TipoVestimentaRepository extends JpaRepository<TipoVestimenta, Integer>, JpaSpecificationExecutor<TipoVestimenta> {
}
