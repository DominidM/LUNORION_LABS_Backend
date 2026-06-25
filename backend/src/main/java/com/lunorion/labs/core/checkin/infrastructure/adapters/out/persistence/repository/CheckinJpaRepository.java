package com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.entity.CheckinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CheckinJpaRepository extends JpaRepository<CheckinEntity, UUID> {
    List<CheckinEntity> findByTenantId(UUID tenantId);
    Optional<CheckinEntity> findByOtId(UUID otId);
}
