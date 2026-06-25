package com.lunorion.labs.core.inventario.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.inventario.domain.entity.MovimientoStock;
import com.lunorion.labs.core.inventario.domain.ports.out.IMovimientoStockRepositoryPort;
import com.lunorion.labs.core.inventario.infrastructure.adapters.out.persistence.mapper.MovimientoStockEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class MovimientoStockRepositoryAdapter implements IMovimientoStockRepositoryPort {

    private final MovimientoStockJpaRepository jpaRepository;
    private final MovimientoStockEntityMapper mapper;

    public MovimientoStockRepositoryAdapter(MovimientoStockJpaRepository jpaRepository, MovimientoStockEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MovimientoStock save(MovimientoStock movimiento) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(movimiento)));
    }

    @Override
    public Optional<MovimientoStock> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<MovimientoStock> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoStock> findByProductoId(String productoId) {
        return jpaRepository.findByProductoId(UUID.fromString(productoId)).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoStock> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(UUID.fromString(id));
    }
}
