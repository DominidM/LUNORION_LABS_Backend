package com.lunorion.labs.core.vehiculo.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.vehiculo.infrastructure.adapters.out.persistence.entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculoJpaRepository extends JpaRepository<VehiculoEntity, String> {
    Optional<VehiculoEntity> findByPlaca(String placa);
    List<VehiculoEntity> findByTenantId(String tenantId);
    List<VehiculoEntity> findByClienteId(String clienteId);
}
