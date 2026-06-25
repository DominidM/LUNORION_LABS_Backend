package com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.entity.PermisoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermisoJpaRepository extends JpaRepository<PermisoEntity, UUID> {
    Optional<PermisoEntity> findByCodigo(String codigo);
    List<PermisoEntity> findByCodigoIn(List<String> codigos);
}
