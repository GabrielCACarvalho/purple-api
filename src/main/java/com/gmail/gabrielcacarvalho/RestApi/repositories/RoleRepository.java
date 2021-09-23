package com.gmail.gabrielcacarvalho.RestApi.repositories;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByNome(String nome);
}
