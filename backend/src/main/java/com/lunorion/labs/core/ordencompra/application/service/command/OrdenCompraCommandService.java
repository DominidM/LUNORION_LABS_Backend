package com.lunorion.labs.core.ordencompra.application.service.command;

import com.lunorion.labs.core.ordencompra.application.dto.in.CreateOrdenCompraRequest;
import com.lunorion.labs.core.ordencompra.application.dto.out.OrdenCompraResponse;
import com.lunorion.labs.core.ordencompra.application.mapper.OrdenCompraMapper;
import com.lunorion.labs.core.ordencompra.domain.entity.OrdenCompra;
import com.lunorion.labs.core.ordencompra.domain.ports.in.IOrdenCompraCommandPort;
import com.lunorion.labs.core.ordencompra.domain.ports.out.IOrdenCompraRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrdenCompraCommandService implements IOrdenCompraCommandPort {

    private final IOrdenCompraRepositoryPort repository;
    private final OrdenCompraMapper mapper;

    public OrdenCompraCommandService(IOrdenCompraRepositoryPort repository, OrdenCompraMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public OrdenCompraResponse create(CreateOrdenCompraRequest request) {
        OrdenCompra ordenCompra = mapper.toDomain(request);
        OrdenCompra saved = repository.save(ordenCompra);
        return mapper.toResponse(saved);
    }

    @Override
    public void aprobar(String id) {
        repository.findById(id).ifPresent(oc -> {
            oc.aprobar();
            repository.save(oc);
        });
    }

    @Override
    public void completar(String id) {
        repository.findById(id).ifPresent(oc -> {
            oc.completar();
            repository.save(oc);
        });
    }

    @Override
    public void anular(String id) {
        repository.findById(id).ifPresent(oc -> {
            oc.anular();
            repository.save(oc);
        });
    }
}
