package com.lunorion.labs.core.cita.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.cita.infrastructure.adapters.out.persistence.entity.CitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CitaJpaRepository extends JpaRepository<CitaEntity, UUID> {
    List<CitaEntity> findByTenantId(UUID tenantId);
    List<CitaEntity> findByClienteId(String clienteId);
    List<CitaEntity> findByTecnicoId(String tecnicoId);
    List<CitaEntity> findByFechaHoraBetween(LocalDateTime desde, LocalDateTime hasta);
    List<CitaEntity> findByTenantIdAndFechaHoraBetween(UUID tenantId, LocalDateTime desde, LocalDateTime hasta);
    List<CitaEntity> findByTecnicoIdAndFechaHoraBetween(String tecnicoId, LocalDateTime desde, LocalDateTime hasta);
}
