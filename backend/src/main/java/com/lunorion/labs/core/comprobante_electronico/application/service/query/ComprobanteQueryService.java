package com.lunorion.labs.core.comprobante_electronico.application.service.query;

import com.lunorion.labs.core.comprobante_electronico.application.dto.out.ComprobanteResponse;
import com.lunorion.labs.core.comprobante_electronico.application.mapper.ComprobanteMapper;
import com.lunorion.labs.core.comprobante_electronico.domain.ports.in.IComprobanteQueryPort;
import com.lunorion.labs.core.comprobante_electronico.domain.ports.out.IComprobanteRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ComprobanteQueryService implements IComprobanteQueryPort {

    private final IComprobanteRepositoryPort repository;
    private final ComprobanteMapper mapper;

    public ComprobanteQueryService(IComprobanteRepositoryPort repository, ComprobanteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ComprobanteResponse> findById(String id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public List<ComprobanteResponse> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComprobanteResponse> findByVentaId(String ventaId) {
        return repository.findByVentaId(ventaId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
