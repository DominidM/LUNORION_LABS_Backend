package com.lunorion.labs.core.tecnico.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.tecnico.infrastructure.adapters.out.persistence.entity.ConfiguracionComisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConfiguracionComisionJpaRepository extends JpaRepository<ConfiguracionComisionEntity, UUID> {
    List<ConfiguracionComisionEntity> findByTecnicoId(UUID tecnicoId);
}
