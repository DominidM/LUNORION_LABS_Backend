package com.lunorion.labs.core.proveedor.application.service.query;

import com.lunorion.labs.core.proveedor.application.dto.out.ProveedorResponse;
import com.lunorion.labs.core.proveedor.application.mapper.ProveedorMapper;
import com.lunorion.labs.core.proveedor.domain.ports.in.IProveedorQueryPort;
import com.lunorion.labs.core.proveedor.domain.ports.out.IProveedorRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProveedorQueryService implements IProveedorQueryPort {

    private final IProveedorRepositoryPort repository;
    private final ProveedorMapper mapper;

    public ProveedorQueryService(IProveedorRepositoryPort repository, ProveedorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ProveedorResponse> findById(String id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public List<ProveedorResponse> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProveedorResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
