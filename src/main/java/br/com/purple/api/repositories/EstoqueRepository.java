package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.EstoquePK;
import br.com.purple.api.core.entity.enumerator.Tamanho;
import br.com.purple.api.core.entity.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository <Estoque, EstoquePK> {
    Estoque getByIdProdutoIdAndIdTamanho(Integer idProduto, Tamanho tamanho);
}
