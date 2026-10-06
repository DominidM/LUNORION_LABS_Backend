package com.lunorion.labs.core.cliente.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.cliente.infrastructure.adapters.out.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, UUID>, JpaSpecificationExecutor<ClienteEntity> {
    Optional<ClienteEntity> findByNumeroDocumento(String numeroDocumento);
    List<ClienteEntity> findByTenantId(UUID tenantId);
}
