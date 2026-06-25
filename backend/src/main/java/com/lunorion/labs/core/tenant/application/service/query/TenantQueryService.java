package com.lunorion.labs.core.tenant.application.service.query;

import com.lunorion.labs.core.tenant.application.dto.out.TenantResponse;
import com.lunorion.labs.core.tenant.application.mapper.TenantMapper;
import com.lunorion.labs.core.tenant.domain.entity.Tenant;
import com.lunorion.labs.core.tenant.domain.ports.in.ITenantQueryPort;
import com.lunorion.labs.core.tenant.domain.ports.out.ITenantRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TenantQueryService implements ITenantQueryPort {

    private final ITenantRepositoryPort repository;
    private final TenantMapper mapper;

    public TenantQueryService(ITenantRepositoryPort repository, TenantMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<TenantResponse> findById(String id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public Optional<TenantResponse> findByRuc(String ruc) {
        return repository.findByRuc(ruc).map(mapper::toResponse);
    }

    @Override
    public List<TenantResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
