package com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.entity.ResumenDiarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResumenDiarioJpaRepository extends JpaRepository<ResumenDiarioEntity, UUID> {
    List<ResumenDiarioEntity> findByTenantId(UUID tenantId);
}
