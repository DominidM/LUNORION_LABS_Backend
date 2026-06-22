package com.lunorion.labs.core.producto.application.service.command;

import com.lunorion.labs.core.producto.application.dto.in.CreateProductoRequest;
import com.lunorion.labs.core.producto.application.dto.out.ProductoResponse;
import com.lunorion.labs.core.producto.application.mapper.ProductoMapper;
import com.lunorion.labs.core.producto.domain.entity.Producto;
import com.lunorion.labs.core.producto.domain.ports.in.IProductoCommandPort;
import com.lunorion.labs.core.producto.domain.ports.out.IProductoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductoCommandService implements IProductoCommandPort {

    private final IProductoRepositoryPort repository;
    private final ProductoMapper mapper;

    public ProductoCommandService(IProductoRepositoryPort repository, ProductoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductoResponse create(CreateProductoRequest request) {
        Producto producto = mapper.toDomain(request);
        Producto saved = repository.save(producto);
        return mapper.toResponse(saved);
    }

    @Override
    public void updateStock(String id, Integer cantidad) {
        repository.findById(id).ifPresent(producto -> {
            producto.updateStock(cantidad);
            repository.save(producto);
        });
    }
}
