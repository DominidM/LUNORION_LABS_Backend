package com.lunorion.labs.core.vehiculo.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.vehiculo.infrastructure.adapters.out.persistence.entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehiculoJpaRepository extends JpaRepository<VehiculoEntity, UUID> {
    Optional<VehiculoEntity> findByPlaca(String placa);
    List<VehiculoEntity> findByTenantId(UUID tenantId);
    List<VehiculoEntity> findByClienteId(UUID clienteId);
}
