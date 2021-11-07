package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Promocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PromocaoRepository extends JpaRepository<Promocao, Integer>, JpaSpecificationExecutor<Promocao> {
}
