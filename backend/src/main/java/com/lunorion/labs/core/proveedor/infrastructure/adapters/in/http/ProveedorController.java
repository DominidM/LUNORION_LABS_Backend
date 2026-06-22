package com.lunorion.labs.core.proveedor.infrastructure.adapters.in.http;

import com.lunorion.labs.core.proveedor.application.dto.in.CreateProveedorRequest;
import com.lunorion.labs.core.proveedor.application.dto.out.ProveedorResponse;
import com.lunorion.labs.core.proveedor.domain.ports.in.IProveedorCommandPort;
import com.lunorion.labs.core.proveedor.domain.ports.in.IProveedorQueryPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final IProveedorCommandPort commandService;
    private final IProveedorQueryPort queryService;

    public ProveedorController(IProveedorCommandPort commandService, IProveedorQueryPort queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<ProveedorResponse> create(@RequestBody CreateProveedorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponse> findById(@PathVariable String id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProveedorResponse>> findAll() {
        return ResponseEntity.ok(queryService.findAll());
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<ProveedorResponse>> findByTenantId(@PathVariable String tenantId) {
        return ResponseEntity.ok(queryService.findByTenantId(tenantId));
    }

    @PostMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivar(@PathVariable String id) {
        commandService.desactivar(id);
        return ResponseEntity.ok().build();
    }
}
