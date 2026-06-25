package com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.entity.OrdenTrabajoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrdenTrabajoJpaRepository extends JpaRepository<OrdenTrabajoEntity, UUID> {
    List<OrdenTrabajoEntity> findByTenantId(UUID tenantId);
    List<OrdenTrabajoEntity> findByEstadoAndTenantId(String estado, UUID tenantId);
    List<OrdenTrabajoEntity> findByClienteId(UUID clienteId);
}
