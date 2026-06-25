package com.lunorion.labs.core.cotizacion.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.cotizacion.infrastructure.adapters.out.persistence.entity.CotizacionItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CotizacionItemJpaRepository extends JpaRepository<CotizacionItemEntity, UUID> {
    List<CotizacionItemEntity> findByCotizacionId(UUID cotizacionId);
}
