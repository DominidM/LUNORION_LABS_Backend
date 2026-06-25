package com.lunorion.labs.core.tecnico.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.tecnico.infrastructure.adapters.out.persistence.entity.TecnicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TecnicoJpaRepository extends JpaRepository<TecnicoEntity, UUID> {
    List<TecnicoEntity> findByTenantId(UUID tenantId);
}
