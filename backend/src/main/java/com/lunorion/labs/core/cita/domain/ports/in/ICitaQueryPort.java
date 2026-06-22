package com.lunorion.labs.core.cita.domain.ports.in;

import com.lunorion.labs.core.cita.application.dto.out.CitaResponse;

import java.util.List;
import java.util.Optional;

public interface ICitaQueryPort {
    Optional<CitaResponse> findById(String id);
    List<CitaResponse> findByTenantId(String tenantId);
    List<CitaResponse> findByClienteId(String clienteId);
    List<CitaResponse> findByTecnicoId(String tecnicoId);
}
