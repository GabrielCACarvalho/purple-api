package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
