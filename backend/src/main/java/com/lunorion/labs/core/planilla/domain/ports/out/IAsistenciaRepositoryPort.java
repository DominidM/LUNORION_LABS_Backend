package com.lunorion.labs.core.planilla.domain.ports.out;

import com.lunorion.labs.core.planilla.domain.entity.Asistencia;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IAsistenciaRepositoryPort {
    Asistencia save(Asistencia asistencia);
    Optional<Asistencia> findById(String id);
    List<Asistencia> findByTecnicoIdAndFechaBetween(String tecnicoId, LocalDate desde, LocalDate hasta);
    List<Asistencia> findByTecnicoId(String tecnicoId);
}
