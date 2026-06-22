package com.lunorion.labs.core.orden_trabajo.application.service.query;

import com.lunorion.labs.core.orden_trabajo.application.dto.out.OrdenTrabajoResponse;
import com.lunorion.labs.core.orden_trabajo.application.mapper.OrdenTrabajoMapper;
import com.lunorion.labs.core.orden_trabajo.domain.ports.in.IOrdenTrabajoQueryPort;
import com.lunorion.labs.core.orden_trabajo.domain.ports.out.IOrdenTrabajoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrdenTrabajoQueryService implements IOrdenTrabajoQueryPort {

    private final IOrdenTrabajoRepositoryPort repository;
    private final OrdenTrabajoMapper mapper;

    public OrdenTrabajoQueryService(IOrdenTrabajoRepositoryPort repository, OrdenTrabajoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<OrdenTrabajoResponse> findById(String id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public List<OrdenTrabajoResponse> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrdenTrabajoResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
