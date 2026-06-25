package com.lunorion.labs.core.planilla.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.planilla.domain.entity.BoletaPago;
import com.lunorion.labs.core.planilla.domain.ports.out.IBoletaPagoRepositoryPort;
import com.lunorion.labs.core.planilla.infrastructure.adapters.out.persistence.mapper.PlanillaEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class BoletaPagoRepositoryAdapter implements IBoletaPagoRepositoryPort {

    private final BoletaPagoJpaRepository jpaRepository;
    private final PlanillaEntityMapper mapper;

    public BoletaPagoRepositoryAdapter(BoletaPagoJpaRepository jpaRepository, PlanillaEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public BoletaPago save(BoletaPago boleta) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(boleta)));
    }

    @Override
    public Optional<BoletaPago> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public Optional<BoletaPago> findByTecnicoIdAndPeriodo(String tecnicoId, String periodo) {
        return jpaRepository.findByTecnicoIdAndPeriodo(UUID.fromString(tecnicoId), periodo)
                .map(mapper::toDomain);
    }

    @Override
    public List<BoletaPago> findByTenantIdAndPeriodo(String tenantId, String periodo) {
        return jpaRepository.findByTenantIdAndPeriodo(UUID.fromString(tenantId), periodo).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }
}
