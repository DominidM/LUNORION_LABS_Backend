package com.lunorion.labs.core.cita.domain.ports.in;

import com.lunorion.labs.core.cita.application.dto.in.CreateCitaRequest;
import com.lunorion.labs.core.cita.application.dto.in.NotificacionesConfigRequest;
import com.lunorion.labs.core.cita.application.dto.in.ReprogramarCitaRequest;
import com.lunorion.labs.core.cita.application.dto.out.CitaResponse;
import com.lunorion.labs.core.cita.application.dto.out.NotificacionesConfigResponse;

import java.util.UUID;

public interface ICitaCommandPort {
    CitaResponse crear(CreateCitaRequest request);
    CitaResponse reprogramar(UUID id, ReprogramarCitaRequest request);
    void confirmar(UUID id);
    void cancelar(UUID id);
    void cambiarEstado(UUID id, String estado);
    NotificacionesConfigResponse updateNotificacionesConfig(NotificacionesConfigRequest request);
}
