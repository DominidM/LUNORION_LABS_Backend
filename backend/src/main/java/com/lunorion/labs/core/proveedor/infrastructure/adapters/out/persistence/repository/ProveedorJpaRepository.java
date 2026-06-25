package com.lunorion.labs.core.proveedor.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.proveedor.infrastructure.adapters.out.persistence.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProveedorJpaRepository extends JpaRepository<ProveedorEntity, UUID> {
    List<ProveedorEntity> findByTenantId(UUID tenantId);
}
