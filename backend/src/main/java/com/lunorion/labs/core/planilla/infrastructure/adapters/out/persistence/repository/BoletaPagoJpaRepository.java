package com.lunorion.labs.core.planilla.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.planilla.infrastructure.adapters.out.persistence.entity.BoletaPagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoletaPagoJpaRepository extends JpaRepository<BoletaPagoEntity, UUID> {
    Optional<BoletaPagoEntity> findByTecnicoIdAndPeriodo(UUID tecnicoId, String periodo);
    List<BoletaPagoEntity> findByTenantIdAndPeriodo(UUID tenantId, String periodo);
}
