package com.lunorion.labs.core.venta.domain.ports.in;

import com.lunorion.labs.core.venta.application.dto.in.CreateVentaRequest;
import com.lunorion.labs.core.venta.application.dto.out.VentaResponse;

public interface IVentaCommandPort {
    VentaResponse create(CreateVentaRequest request);
    void delete(String id);
}
