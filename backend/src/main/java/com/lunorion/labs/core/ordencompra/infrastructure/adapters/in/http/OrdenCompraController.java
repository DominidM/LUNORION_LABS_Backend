package com.lunorion.labs.core.ordencompra.infrastructure.adapters.in.http;

import com.lunorion.labs.core.ordencompra.application.dto.in.CreateOrdenCompraRequest;
import com.lunorion.labs.core.ordencompra.application.dto.out.OrdenCompraItemResponse;
import com.lunorion.labs.core.ordencompra.application.dto.out.OrdenCompraResponse;
import com.lunorion.labs.core.ordencompra.application.service.query.OrdenCompraQueryService;
import com.lunorion.labs.core.ordencompra.domain.ports.in.IOrdenCompraCommandPort;
import com.lunorion.labs.core.ordencompra.domain.ports.in.IOrdenCompraQueryPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-compra")
public class OrdenCompraController {

    private final IOrdenCompraCommandPort commandService;
    private final IOrdenCompraQueryPort queryService;
    private final OrdenCompraQueryService queryServiceExtended;

    public OrdenCompraController(IOrdenCompraCommandPort commandService,
                                 IOrdenCompraQueryPort queryService,
                                 OrdenCompraQueryService queryServiceExtended) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.queryServiceExtended = queryServiceExtended;
    }

    @PostMapping
    public ResponseEntity<OrdenCompraResponse> create(@RequestBody CreateOrdenCompraRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompraResponse> findById(@PathVariable String id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<OrdenCompraResponse>> findByTenant(@PathVariable String tenantId) {
        return ResponseEntity.ok(queryService.findByTenantId(tenantId));
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<OrdenCompraResponse>> findByProveedor(@PathVariable String proveedorId) {
        return ResponseEntity.ok(queryService.findByProveedorId(proveedorId));
    }

    @PostMapping("/{id}/aprobar")
    public ResponseEntity<Void> aprobar(@PathVariable String id) {
        commandService.aprobar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/completar")
    public ResponseEntity<Void> completar(@PathVariable String id) {
        commandService.completar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/anular")
    public ResponseEntity<Void> anular(@PathVariable String id) {
        commandService.anular(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<OrdenCompraItemResponse>> findItems(@PathVariable String id) {
        return ResponseEntity.ok(queryServiceExtended.findItemsByOrdenCompraId(id));
    }
}
