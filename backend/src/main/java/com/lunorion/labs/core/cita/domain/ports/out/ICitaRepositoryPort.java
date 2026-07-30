package com.lunorion.labs.core.cita.domain.ports.out;

import com.lunorion.labs.core.cita.domain.entity.Cita;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICitaRepositoryPort {
    Cita save(Cita cita);
    Optional<Cita> findById(UUID id);
    List<Cita> findByTenantId(String tenantId);
    List<Cita> findByClienteId(UUID clienteId);
    List<Cita> findByTecnicoId(UUID tecnicoId);
    List<Cita> findByFechaHoraBetween(LocalDate desde, LocalDate hasta);
    List<Cita> findByTecnicoIdAndFechaHoraBetween(UUID tecnicoId, LocalDate desde, LocalDate hasta);
    void deleteById(UUID id);
}
