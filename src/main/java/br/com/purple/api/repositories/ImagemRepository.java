package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
    List<Imagem> findByProdutoId(Integer idProduto);
}
