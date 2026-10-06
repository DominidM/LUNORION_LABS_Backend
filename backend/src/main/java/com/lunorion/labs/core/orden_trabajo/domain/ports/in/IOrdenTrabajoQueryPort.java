package com.lunorion.labs.core.orden_trabajo.domain.ports.in;

import com.lunorion.labs.core.orden_trabajo.application.dto.out.KanbanResponse;
import com.lunorion.labs.core.orden_trabajo.application.dto.out.OrdenTrabajoResponse;
import com.lunorion.labs.core.orden_trabajo.domain.filter.OrdenTrabajoFiltro;
import com.lunorion.labs.shared.application.dto.out.PagedResponse;

import java.util.List;
import java.util.Optional;

public interface IOrdenTrabajoQueryPort {
    Optional<OrdenTrabajoResponse> findById(String id);
    List<OrdenTrabajoResponse> findByTenantId(String tenantId);
    List<OrdenTrabajoResponse> findAll();
    PagedResponse<OrdenTrabajoResponse> search(OrdenTrabajoFiltro filtro);
    List<OrdenTrabajoResponse> searchAll(OrdenTrabajoFiltro filtro);
    List<OrdenTrabajoResponse> findByEstado(String estado, String tenantId);
    List<KanbanResponse> kanban(String tenantId);
}
