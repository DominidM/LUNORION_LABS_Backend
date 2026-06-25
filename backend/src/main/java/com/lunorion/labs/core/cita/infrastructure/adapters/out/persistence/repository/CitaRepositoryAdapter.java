package com.lunorion.labs.core.cita.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.cita.domain.entity.Cita;
import com.lunorion.labs.core.cita.domain.ports.out.ICitaRepositoryPort;
import com.lunorion.labs.core.cita.infrastructure.adapters.out.persistence.mapper.CitaEntityMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CitaRepositoryAdapter implements ICitaRepositoryPort {

    private final CitaJpaRepository jpaRepository;
    private final CitaEntityMapper mapper;

    public CitaRepositoryAdapter(CitaJpaRepository jpaRepository, CitaEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Cita save(Cita cita) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(cita)));
    }

    @Override
    public Optional<Cita> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<Cita> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Cita> findByClienteId(String clienteId) {
        return jpaRepository.findByClienteId(clienteId).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Cita> findByTecnicoId(String tecnicoId) {
        return jpaRepository.findByTecnicoId(tecnicoId).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<Cita> findByFechaHoraBetween(LocalDate desde, LocalDate hasta) {
        return jpaRepository.findByFechaHoraBetween(
                desde.atStartOfDay(), hasta.atTime(LocalTime.MAX)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Cita> findByTecnicoIdAndFechaHoraBetween(String tecnicoId, LocalDate desde, LocalDate hasta) {
        return jpaRepository.findByTecnicoIdAndFechaHoraBetween(
                tecnicoId, desde.atStartOfDay(), hasta.atTime(LocalTime.MAX)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }
}
