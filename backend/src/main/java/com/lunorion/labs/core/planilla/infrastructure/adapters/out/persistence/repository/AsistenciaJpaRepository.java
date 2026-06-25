package com.lunorion.labs.core.planilla.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.planilla.infrastructure.adapters.out.persistence.entity.AsistenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AsistenciaJpaRepository extends JpaRepository<AsistenciaEntity, UUID> {
    List<AsistenciaEntity> findByTecnicoIdAndFechaBetween(UUID tecnicoId, LocalDate desde, LocalDate hasta);
    List<AsistenciaEntity> findByTecnicoId(UUID tecnicoId);
}
