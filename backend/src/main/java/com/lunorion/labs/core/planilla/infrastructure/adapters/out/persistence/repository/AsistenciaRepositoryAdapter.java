package com.lunorion.labs.core.planilla.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.planilla.domain.entity.Asistencia;
import com.lunorion.labs.core.planilla.domain.ports.out.IAsistenciaRepositoryPort;
import com.lunorion.labs.core.planilla.infrastructure.adapters.out.persistence.mapper.PlanillaEntityMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class AsistenciaRepositoryAdapter implements IAsistenciaRepositoryPort {

    private final AsistenciaJpaRepository jpaRepository;
    private final PlanillaEntityMapper mapper;

    public AsistenciaRepositoryAdapter(AsistenciaJpaRepository jpaRepository, PlanillaEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Asistencia save(Asistencia asistencia) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(asistencia)));
    }

    @Override
    public Optional<Asistencia> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<Asistencia> findByTecnicoIdAndFechaBetween(String tecnicoId, LocalDate desde, LocalDate hasta) {
        return jpaRepository.findByTecnicoIdAndFechaBetween(UUID.fromString(tecnicoId), desde, hasta)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Asistencia> findByTecnicoId(String tecnicoId) {
        return jpaRepository.findByTecnicoId(UUID.fromString(tecnicoId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }
}
