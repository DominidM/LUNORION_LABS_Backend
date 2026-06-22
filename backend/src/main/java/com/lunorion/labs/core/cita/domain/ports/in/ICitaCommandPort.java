package com.lunorion.labs.core.cita.domain.ports.in;

import com.lunorion.labs.core.cita.application.dto.in.CreateCitaRequest;
import com.lunorion.labs.core.cita.application.dto.out.CitaResponse;

public interface ICitaCommandPort {
    CitaResponse crear(CreateCitaRequest request);
    void confirmar(String id);
    void cancelar(String id);
}
