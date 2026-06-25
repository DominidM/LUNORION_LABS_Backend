package com.lunorion.labs.core.venta.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.venta.infrastructure.adapters.out.persistence.entity.VentaItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VentaItemJpaRepository extends JpaRepository<VentaItemEntity, UUID> {
    List<VentaItemEntity> findByVentaId(UUID ventaId);
}
