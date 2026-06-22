package com.lunorion.labs.core.orden_trabajo.domain.ports.in;

import com.lunorion.labs.core.orden_trabajo.application.dto.in.CreateOrdenTrabajoRequest;
import com.lunorion.labs.core.orden_trabajo.application.dto.out.OrdenTrabajoResponse;

public interface IOrdenTrabajoCommandPort {
    OrdenTrabajoResponse create(CreateOrdenTrabajoRequest request);
    void delete(String id);
}
