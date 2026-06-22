package com.lunorion.labs.core.tenant.application.service.command;

import com.lunorion.labs.core.tenant.application.dto.in.CreateTenantRequest;
import com.lunorion.labs.core.tenant.application.dto.out.TenantResponse;
import com.lunorion.labs.core.tenant.application.mapper.TenantMapper;
import com.lunorion.labs.core.tenant.domain.entity.Tenant;
import com.lunorion.labs.core.tenant.domain.ports.in.ITenantCommandPort;
import com.lunorion.labs.core.tenant.domain.ports.out.ITenantRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TenantCommandService implements ITenantCommandPort {

    private final ITenantRepositoryPort repository;
    private final TenantMapper mapper;

    public TenantCommandService(ITenantRepositoryPort repository, TenantMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TenantResponse create(CreateTenantRequest request) {
        Tenant tenant = mapper.toDomain(request);
        Tenant saved = repository.save(tenant);
        return mapper.toResponse(saved);
    }

    @Override
    public void update(String tenantId, CreateTenantRequest request) {
        repository.findById(tenantId).ifPresent(tenant -> {
            tenant.actualizarDatos(
                request.getRazonSocial(),
                request.getNombreComercial(),
                request.getDomicilioFiscal(),
                request.getEmail(),
                request.getTelefono()
            );
            repository.save(tenant);
        });
    }

    @Override
    public void delete(String tenantId) {
        repository.deleteById(tenantId);
    }
}
