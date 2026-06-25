package com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.entity.ComprobanteElectronicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ComprobanteJpaRepository extends JpaRepository<ComprobanteElectronicoEntity, UUID> {
    List<ComprobanteElectronicoEntity> findByTenantId(UUID tenantId);
    List<ComprobanteElectronicoEntity> findByVentaId(String ventaId);
    List<ComprobanteElectronicoEntity> findByTenantIdAndFechaEmisionBetween(UUID tenantId, LocalDate fechaInicio, LocalDate fechaFin);
}
