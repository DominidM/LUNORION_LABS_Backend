package com.lunorion.labs.core.cotizacion.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.cotizacion.infrastructure.adapters.out.persistence.entity.CotizacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CotizacionJpaRepository extends JpaRepository<CotizacionEntity, UUID> {
    List<CotizacionEntity> findByTenantId(UUID tenantId);
    List<CotizacionEntity> findByClienteId(UUID clienteId);
    List<CotizacionEntity> findByEstado(String estado);
}
