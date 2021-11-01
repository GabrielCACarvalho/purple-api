package com.gmail.gabrielcacarvalho.RestApi.repositories;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
}