package com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.entity.OtManoObraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OtManoObraJpaRepository extends JpaRepository<OtManoObraEntity, UUID> {
    List<OtManoObraEntity> findByOrdenTrabajoId(UUID ordenTrabajoId);
}
