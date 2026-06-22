package com.lunorion.labs.core.venta.application.service.command;

import com.lunorion.labs.core.venta.application.dto.in.CreateVentaRequest;
import com.lunorion.labs.core.venta.application.dto.out.VentaResponse;
import com.lunorion.labs.core.venta.application.mapper.VentaMapper;
import com.lunorion.labs.core.venta.domain.entity.Venta;
import com.lunorion.labs.core.venta.domain.entity.VentaItem;
import com.lunorion.labs.core.venta.domain.ports.in.IVentaCommandPort;
import com.lunorion.labs.core.venta.domain.ports.out.IVentaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VentaCommandService implements IVentaCommandPort {

    private final IVentaRepositoryPort repository;
    private final VentaMapper mapper;

    public VentaCommandService(IVentaRepositoryPort repository, VentaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public VentaResponse create(CreateVentaRequest request) {
        Venta venta = mapper.toDomain(request);
        Venta saved = repository.save(venta);

        List<VentaItem> items = mapper.toItemsDomain(saved.getId().toString(), request.getItems());
        repository.saveAllItems(items);

        VentaResponse response = mapper.toResponse(saved);
        response.setItems(mapper.toItemsResponse(items));
        return response;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
