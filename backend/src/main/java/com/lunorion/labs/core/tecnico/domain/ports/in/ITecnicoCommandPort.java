package com.lunorion.labs.core.tecnico.domain.ports.in;

import com.lunorion.labs.core.tecnico.application.dto.in.CreateTecnicoRequest;
import com.lunorion.labs.core.tecnico.application.dto.out.TecnicoResponse;

import java.math.BigDecimal;

public interface ITecnicoCommandPort {
    TecnicoResponse create(CreateTecnicoRequest request);
    void desactivar(String id);
    void actualizarTarifa(String id, BigDecimal tarifa);
}
