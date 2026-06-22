package com.lunorion.labs.core.planilla.domain.ports.in;

import com.lunorion.labs.core.planilla.application.dto.in.RegistrarAsistenciaRequest;
import com.lunorion.labs.core.planilla.application.dto.out.AsistenciaResponse;
import com.lunorion.labs.core.planilla.application.dto.out.BoletaPagoResponse;

public interface IPlanillaCommandPort {
    AsistenciaResponse registrarAsistencia(RegistrarAsistenciaRequest request);
    BoletaPagoResponse generarBoleta(String tecnicoId, String periodo);
}
