package com.lunorion.labs.core.caja.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.caja.infrastructure.adapters.out.persistence.entity.CierreCajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CierreCajaJpaRepository extends JpaRepository<CierreCajaEntity, UUID> {
    List<CierreCajaEntity> findByTenantId(UUID tenantId);
    Optional<CierreCajaEntity> findByTenantIdAndHoraCierreIsNull(UUID tenantId);
}
