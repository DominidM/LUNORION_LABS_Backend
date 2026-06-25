package com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.comprobante_electronico.domain.entity.ResumenDiario;
import com.lunorion.labs.core.comprobante_electronico.domain.ports.out.IResumenDiarioRepositoryPort;
import com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.mapper.ResumenDiarioEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ResumenDiarioRepositoryAdapter implements IResumenDiarioRepositoryPort {

    private final ResumenDiarioJpaRepository jpaRepository;
    private final ResumenDiarioEntityMapper mapper;

    public ResumenDiarioRepositoryAdapter(ResumenDiarioJpaRepository jpaRepository, ResumenDiarioEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ResumenDiario save(ResumenDiario resumen) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(resumen)));
    }

    @Override
    public Optional<ResumenDiario> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<ResumenDiario> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }
}
