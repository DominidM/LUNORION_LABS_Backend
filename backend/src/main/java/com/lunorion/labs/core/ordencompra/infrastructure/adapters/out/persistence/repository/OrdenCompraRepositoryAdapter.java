package com.lunorion.labs.core.ordencompra.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.ordencompra.domain.entity.OrdenCompra;
import com.lunorion.labs.core.ordencompra.domain.ports.out.IOrdenCompraRepositoryPort;
import com.lunorion.labs.core.ordencompra.infrastructure.adapters.out.persistence.mapper.OrdenCompraEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class OrdenCompraRepositoryAdapter implements IOrdenCompraRepositoryPort {

    private final OrdenCompraJpaRepository jpaRepository;
    private final OrdenCompraEntityMapper mapper;

    public OrdenCompraRepositoryAdapter(OrdenCompraJpaRepository jpaRepository, OrdenCompraEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public OrdenCompra save(OrdenCompra ordenCompra) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(ordenCompra)));
    }

    @Override
    public Optional<OrdenCompra> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<OrdenCompra> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<OrdenCompra> findByProveedorId(String proveedorId) {
        return jpaRepository.findByProveedorId(UUID.fromString(proveedorId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(UUID.fromString(id));
    }
}
