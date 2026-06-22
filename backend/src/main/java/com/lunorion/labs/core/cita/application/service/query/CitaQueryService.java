package com.lunorion.labs.core.cita.application.service.query;

import com.lunorion.labs.core.cita.application.dto.out.CitaResponse;
import com.lunorion.labs.core.cita.application.mapper.CitaMapper;
import com.lunorion.labs.core.cita.domain.ports.in.ICitaQueryPort;
import com.lunorion.labs.core.cita.domain.ports.out.ICitaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CitaQueryService implements ICitaQueryPort {

    private final ICitaRepositoryPort repository;
    private final CitaMapper mapper;

    public CitaQueryService(ICitaRepositoryPort repository, CitaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<CitaResponse> findById(String id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public List<CitaResponse> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaResponse> findByClienteId(String clienteId) {
        return repository.findByClienteId(clienteId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaResponse> findByTecnicoId(String tecnicoId) {
        return repository.findByTecnicoId(tecnicoId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
