package com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, UUID> {
    Optional<UsuarioEntity> findByEmail(String email);
    List<UsuarioEntity> findByTenantId(UUID tenantId);
    List<UsuarioEntity> findByTenantIdAndActivoTrue(UUID tenantId);
}
