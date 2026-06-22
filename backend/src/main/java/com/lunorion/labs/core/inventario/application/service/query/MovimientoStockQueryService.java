package com.lunorion.labs.core.inventario.application.service.query;

import com.lunorion.labs.core.inventario.application.dto.out.MovimientoStockResponse;
import com.lunorion.labs.core.inventario.application.mapper.MovimientoStockMapper;
import com.lunorion.labs.core.inventario.domain.ports.in.IMovimientoStockQueryPort;
import com.lunorion.labs.core.inventario.domain.ports.out.IMovimientoStockRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MovimientoStockQueryService implements IMovimientoStockQueryPort {

    private final IMovimientoStockRepositoryPort repository;
    private final MovimientoStockMapper mapper;

    public MovimientoStockQueryService(IMovimientoStockRepositoryPort repository, MovimientoStockMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<MovimientoStockResponse> findById(String id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public List<MovimientoStockResponse> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoStockResponse> findByProductoId(String productoId) {
        return repository.findByProductoId(productoId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoStockResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
