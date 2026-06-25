package com.lunorion.labs.core.ordencompra.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.ordencompra.infrastructure.adapters.out.persistence.entity.OrdenCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrdenCompraJpaRepository extends JpaRepository<OrdenCompraEntity, UUID> {
    List<OrdenCompraEntity> findByTenantId(UUID tenantId);
    List<OrdenCompraEntity> findByProveedorId(UUID proveedorId);
}
