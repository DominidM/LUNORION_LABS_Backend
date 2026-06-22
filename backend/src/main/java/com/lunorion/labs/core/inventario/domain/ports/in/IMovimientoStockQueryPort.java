package com.lunorion.labs.core.inventario.domain.ports.in;

import com.lunorion.labs.core.inventario.application.dto.out.MovimientoStockResponse;

import java.util.List;
import java.util.Optional;

public interface IMovimientoStockQueryPort {
    Optional<MovimientoStockResponse> findById(String id);
    List<MovimientoStockResponse> findByTenantId(String tenantId);
    List<MovimientoStockResponse> findByProductoId(String productoId);
    List<MovimientoStockResponse> findAll();
}
