package br.com.purple.api.repositories;

import br.com.purple.api.core.entity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByNome(String nome);
}
