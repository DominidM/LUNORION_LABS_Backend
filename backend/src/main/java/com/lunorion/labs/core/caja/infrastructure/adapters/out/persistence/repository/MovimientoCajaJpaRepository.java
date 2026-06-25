package com.lunorion.labs.core.caja.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.caja.infrastructure.adapters.out.persistence.entity.MovimientoCajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MovimientoCajaJpaRepository extends JpaRepository<MovimientoCajaEntity, UUID> {
    List<MovimientoCajaEntity> findByCierreCajaId(UUID cierreCajaId);
}
