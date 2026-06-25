package com.lunorion.labs.core.venta.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.venta.infrastructure.adapters.out.persistence.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VentaJpaRepository extends JpaRepository<VentaEntity, UUID> {
    List<VentaEntity> findByTenantId(UUID tenantId);
    List<VentaEntity> findByClienteId(UUID clienteId);
}
