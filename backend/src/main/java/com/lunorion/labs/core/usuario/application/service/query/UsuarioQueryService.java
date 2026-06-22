package com.lunorion.labs.core.usuario.application.service.query;

import com.lunorion.labs.core.usuario.application.dto.out.UsuarioResponse;
import com.lunorion.labs.core.usuario.application.mapper.UsuarioMapper;
import com.lunorion.labs.core.usuario.domain.ports.in.IUsuarioQueryPort;
import com.lunorion.labs.core.usuario.domain.ports.out.IUsuarioRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UsuarioQueryService implements IUsuarioQueryPort {

    private final IUsuarioRepositoryPort repository;
    private final UsuarioMapper mapper;

    public UsuarioQueryService(IUsuarioRepositoryPort repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<UsuarioResponse> findById(String id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public Optional<UsuarioResponse> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toResponse);
    }

    @Override
    public List<UsuarioResponse> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
