package com.lunorion.labs.core.producto.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.producto.infrastructure.adapters.out.persistence.entity.CategoriaProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaProductoJpaRepository extends JpaRepository<CategoriaProductoEntity, UUID> {
}
