package com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.entity.CheckinFotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CheckinFotoJpaRepository extends JpaRepository<CheckinFotoEntity, UUID> {
    List<CheckinFotoEntity> findByCheckinId(UUID checkinId);
}
