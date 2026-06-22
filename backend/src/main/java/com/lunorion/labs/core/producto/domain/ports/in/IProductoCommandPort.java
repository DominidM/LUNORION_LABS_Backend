package com.lunorion.labs.core.producto.domain.ports.in;

import com.lunorion.labs.core.producto.application.dto.in.CreateProductoRequest;
import com.lunorion.labs.core.producto.application.dto.out.ProductoResponse;

public interface IProductoCommandPort {
    ProductoResponse create(CreateProductoRequest request);
    void updateStock(String id, Integer cantidad);
}
