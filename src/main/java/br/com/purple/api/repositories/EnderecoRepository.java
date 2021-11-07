package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>, JpaSpecificationExecutor<Endereco> {
}
