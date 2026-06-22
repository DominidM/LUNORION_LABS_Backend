package com.lunorion.labs.core.inventario.application.service.command;

import com.lunorion.labs.core.inventario.application.dto.in.CreateMovimientoStockRequest;
import com.lunorion.labs.core.inventario.application.dto.out.MovimientoStockResponse;
import com.lunorion.labs.core.inventario.application.mapper.MovimientoStockMapper;
import com.lunorion.labs.core.inventario.domain.entity.MovimientoStock;
import com.lunorion.labs.core.inventario.domain.ports.in.IMovimientoStockCommandPort;
import com.lunorion.labs.core.inventario.domain.ports.out.IMovimientoStockRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovimientoStockCommandService implements IMovimientoStockCommandPort {

    private final IMovimientoStockRepositoryPort repository;
    private final MovimientoStockMapper mapper;

    public MovimientoStockCommandService(IMovimientoStockRepositoryPort repository, MovimientoStockMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MovimientoStockResponse create(CreateMovimientoStockRequest request) {
        MovimientoStock movimiento = mapper.toDomain(request);
        MovimientoStock saved = repository.save(movimiento);
        return mapper.toResponse(saved);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
