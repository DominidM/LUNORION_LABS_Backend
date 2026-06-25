package com.lunorion.labs.core.tenant.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.tenant.domain.entity.Tenant;
import com.lunorion.labs.core.tenant.domain.ports.out.ITenantRepositoryPort;
import com.lunorion.labs.core.tenant.infrastructure.adapters.out.persistence.mapper.TenantEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TenantRepositoryAdapter implements ITenantRepositoryPort {

    private final TenantJpaRepository jpaRepository;
    private final TenantEntityMapper mapper;

    public TenantRepositoryAdapter(TenantJpaRepository jpaRepository, TenantEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Tenant save(Tenant tenant) {
        var entity = mapper.toEntity(tenant);
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Tenant> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public Optional<Tenant> findByRuc(String ruc) {
        return jpaRepository.findByRuc(ruc).map(mapper::toDomain);
    }

    @Override
    public List<Tenant> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(UUID.fromString(id));
    }
}
