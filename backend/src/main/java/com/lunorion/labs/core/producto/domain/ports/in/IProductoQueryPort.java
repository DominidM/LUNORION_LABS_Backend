package com.lunorion.labs.core.producto.domain.ports.in;

import com.lunorion.labs.core.producto.application.dto.out.ProductoResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductoQueryPort {
    Optional<ProductoResponse> findById(String id);
    Optional<ProductoResponse> findByCodigo(String codigo);
    List<ProductoResponse> findByTenantId(String tenantId);
    List<ProductoResponse> findByCategoriaId(UUID categoriaId);
}
