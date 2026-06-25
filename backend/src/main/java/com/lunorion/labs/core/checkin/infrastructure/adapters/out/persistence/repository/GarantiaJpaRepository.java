package com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.entity.GarantiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GarantiaJpaRepository extends JpaRepository<GarantiaEntity, UUID> {
    List<GarantiaEntity> findByTenantId(UUID tenantId);
}
