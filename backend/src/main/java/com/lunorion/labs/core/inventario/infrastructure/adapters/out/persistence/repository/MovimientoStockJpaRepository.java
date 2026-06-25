package com.lunorion.labs.core.inventario.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.inventario.infrastructure.adapters.out.persistence.entity.MovimientoStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MovimientoStockJpaRepository extends JpaRepository<MovimientoStockEntity, UUID> {
    List<MovimientoStockEntity> findByTenantId(UUID tenantId);
    List<MovimientoStockEntity> findByProductoId(UUID productoId);
}
