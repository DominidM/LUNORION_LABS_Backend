package com.lunorion.labs.core.orden_trabajo.application.service.command;

import com.lunorion.labs.core.orden_trabajo.application.dto.in.CreateOrdenTrabajoRequest;
import com.lunorion.labs.core.orden_trabajo.application.dto.out.OrdenTrabajoResponse;
import com.lunorion.labs.core.orden_trabajo.application.mapper.OrdenTrabajoMapper;
import com.lunorion.labs.core.orden_trabajo.domain.entity.OrdenTrabajo;
import com.lunorion.labs.core.orden_trabajo.domain.entity.OtInsumo;
import com.lunorion.labs.core.orden_trabajo.domain.entity.OtManoObra;
import com.lunorion.labs.core.orden_trabajo.domain.ports.in.IOrdenTrabajoCommandPort;
import com.lunorion.labs.core.orden_trabajo.domain.ports.out.IOrdenTrabajoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdenTrabajoCommandService implements IOrdenTrabajoCommandPort {

    private final IOrdenTrabajoRepositoryPort repository;
    private final OrdenTrabajoMapper mapper;

    public OrdenTrabajoCommandService(IOrdenTrabajoRepositoryPort repository, OrdenTrabajoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public OrdenTrabajoResponse create(CreateOrdenTrabajoRequest request) {
        OrdenTrabajo ot = mapper.toDomain(request);
        OrdenTrabajo saved = repository.save(ot);

        List<OtInsumo> insumos = mapper.toInsumosDomain(saved.getId().toString(), request.getInsumos());
        repository.saveAllInsumos(insumos);

        List<OtManoObra> manosObra = mapper.toManosObraDomain(saved.getId().toString(), request.getManosObra());
        repository.saveAllManoObra(manosObra);

        OrdenTrabajoResponse response = mapper.toResponse(saved);
        response.setInsumos(mapper.toInsumosResponse(insumos));
        response.setManosObra(mapper.toManosObraResponse(manosObra));
        return response;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
