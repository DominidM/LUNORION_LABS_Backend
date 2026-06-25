package com.lunorion.labs.core.ordencompra.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.ordencompra.infrastructure.adapters.out.persistence.entity.OrdenCompraItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrdenCompraItemJpaRepository extends JpaRepository<OrdenCompraItemEntity, UUID> {
    List<OrdenCompraItemEntity> findByOrdenCompraId(UUID ordenCompraId);
}
