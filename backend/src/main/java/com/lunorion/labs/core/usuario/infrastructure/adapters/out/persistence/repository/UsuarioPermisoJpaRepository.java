package com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.entity.UsuarioPermisoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UsuarioPermisoJpaRepository extends JpaRepository<UsuarioPermisoEntity, UUID> {
    List<UsuarioPermisoEntity> findByUsuarioId(UUID usuarioId);
    void deleteByUsuarioId(UUID usuarioId);
}
