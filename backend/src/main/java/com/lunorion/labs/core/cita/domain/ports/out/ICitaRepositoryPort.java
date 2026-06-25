package com.lunorion.labs.core.cita.domain.ports.out;

import com.lunorion.labs.core.cita.domain.entity.Cita;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICitaRepositoryPort {
    Cita save(Cita cita);
    Optional<Cita> findById(String id);
    List<Cita> findByTenantId(String tenantId);
    List<Cita> findByClienteId(String clienteId);
    List<Cita> findByTecnicoId(String tecnicoId);
    List<Cita> findByFechaHoraBetween(LocalDate desde, LocalDate hasta);
    List<Cita> findByTecnicoIdAndFechaHoraBetween(String tecnicoId, LocalDate desde, LocalDate hasta);
    void deleteById(String id);
}
