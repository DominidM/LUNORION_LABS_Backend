package com.lunorion.labs.core.cita.domain.ports.in;

import com.lunorion.labs.core.cita.application.dto.out.CitaResponse;
import com.lunorion.labs.core.cita.application.dto.out.DisponibilidadResponse;
import com.lunorion.labs.core.cita.application.dto.out.NotificacionesConfigResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICitaQueryPort {
    Optional<CitaResponse> findById(UUID id);
    List<CitaResponse> findByTenantId(String tenantId);
    List<CitaResponse> findByClienteId(UUID clienteId);
    List<CitaResponse> findByTecnicoId(UUID tecnicoId);
    List<CitaResponse> calendario(String tenantId, LocalDate desde, LocalDate hasta);
    List<DisponibilidadResponse> disponibilidad(LocalDate fecha, UUID tecnicoId);
    NotificacionesConfigResponse getNotificacionesConfig(String tenantId);
}
