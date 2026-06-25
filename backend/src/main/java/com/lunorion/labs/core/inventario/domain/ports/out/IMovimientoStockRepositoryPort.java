package com.lunorion.labs.core.inventario.domain.ports.out;

import com.lunorion.labs.core.inventario.domain.entity.MovimientoStock;

import java.util.List;
import java.util.Optional;

public interface IMovimientoStockRepositoryPort {
    MovimientoStock save(MovimientoStock movimiento);
    Optional<MovimientoStock> findById(String id);
    List<MovimientoStock> findByTenantId(String tenantId);
    List<MovimientoStock> findByProductoId(String productoId);
    List<MovimientoStock> findAll();
    void deleteById(String id);
}
