package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
}
