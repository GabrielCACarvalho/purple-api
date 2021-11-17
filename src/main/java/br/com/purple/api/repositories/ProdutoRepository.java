package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Produto;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer>, JpaSpecificationExecutor<Produto> {
}
