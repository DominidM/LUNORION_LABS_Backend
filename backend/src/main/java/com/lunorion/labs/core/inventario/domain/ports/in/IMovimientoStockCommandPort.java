package com.lunorion.labs.core.inventario.domain.ports.in;

import com.lunorion.labs.core.inventario.application.dto.in.CreateMovimientoStockRequest;
import com.lunorion.labs.core.inventario.application.dto.out.MovimientoStockResponse;

public interface IMovimientoStockCommandPort {
    MovimientoStockResponse create(CreateMovimientoStockRequest request);
    void delete(String id);
}
