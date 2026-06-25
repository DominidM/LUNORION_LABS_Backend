package com.lunorion.labs.core.proveedor.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.proveedor.domain.entity.Proveedor;
import com.lunorion.labs.core.proveedor.domain.ports.out.IProveedorRepositoryPort;
import com.lunorion.labs.core.proveedor.infrastructure.adapters.out.persistence.mapper.ProveedorEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProveedorRepositoryAdapter implements IProveedorRepositoryPort {

    private final ProveedorJpaRepository jpaRepository;
    private final ProveedorEntityMapper mapper;

    public ProveedorRepositoryAdapter(ProveedorJpaRepository jpaRepository, ProveedorEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(proveedor)));
    }

    @Override
    public Optional<Proveedor> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<Proveedor> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Proveedor> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }
}
