package com.lunorion.labs.core.proveedor.application.service.command;

import com.lunorion.labs.core.proveedor.application.dto.in.CreateProveedorRequest;
import com.lunorion.labs.core.proveedor.application.dto.out.ProveedorResponse;
import com.lunorion.labs.core.proveedor.application.mapper.ProveedorMapper;
import com.lunorion.labs.core.proveedor.domain.entity.Proveedor;
import com.lunorion.labs.core.proveedor.domain.ports.in.IProveedorCommandPort;
import com.lunorion.labs.core.proveedor.domain.ports.out.IProveedorRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProveedorCommandService implements IProveedorCommandPort {

    private final IProveedorRepositoryPort repository;
    private final ProveedorMapper mapper;

    public ProveedorCommandService(IProveedorRepositoryPort repository, ProveedorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProveedorResponse create(CreateProveedorRequest request) {
        Proveedor proveedor = mapper.toDomain(request);
        Proveedor saved = repository.save(proveedor);
        return mapper.toResponse(saved);
    }

    @Override
    public void desactivar(String id) {
        repository.findById(id).ifPresent(proveedor -> {
            proveedor.desactivar();
            repository.save(proveedor);
        });
    }
}
