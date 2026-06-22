package com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.in.http;

import com.lunorion.labs.core.orden_trabajo.application.dto.in.CreateOrdenTrabajoRequest;
import com.lunorion.labs.core.orden_trabajo.application.dto.out.OrdenTrabajoResponse;
import com.lunorion.labs.core.orden_trabajo.domain.ports.in.IOrdenTrabajoCommandPort;
import com.lunorion.labs.core.orden_trabajo.domain.ports.in.IOrdenTrabajoQueryPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-trabajo")
public class OrdenTrabajoController {

    private final IOrdenTrabajoCommandPort commandService;
    private final IOrdenTrabajoQueryPort queryService;

    public OrdenTrabajoController(IOrdenTrabajoCommandPort commandService, IOrdenTrabajoQueryPort queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<OrdenTrabajoResponse> create(@RequestBody CreateOrdenTrabajoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenTrabajoResponse> findById(@PathVariable String id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<OrdenTrabajoResponse>> findAll() {
        return ResponseEntity.ok(queryService.findAll());
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<OrdenTrabajoResponse>> findByTenant(@PathVariable String tenantId) {
        return ResponseEntity.ok(queryService.findByTenantId(tenantId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        commandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
