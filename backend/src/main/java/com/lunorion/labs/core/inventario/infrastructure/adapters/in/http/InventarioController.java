package com.lunorion.labs.core.inventario.infrastructure.adapters.in.http;

import com.lunorion.labs.core.inventario.application.dto.in.CreateMovimientoStockRequest;
import com.lunorion.labs.core.inventario.application.dto.out.MovimientoStockResponse;
import com.lunorion.labs.core.inventario.domain.ports.in.IMovimientoStockCommandPort;
import com.lunorion.labs.core.inventario.domain.ports.in.IMovimientoStockQueryPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final IMovimientoStockCommandPort commandService;
    private final IMovimientoStockQueryPort queryService;

    public InventarioController(IMovimientoStockCommandPort commandService, IMovimientoStockQueryPort queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping("/movimientos")
    public ResponseEntity<MovimientoStockResponse> create(@RequestBody CreateMovimientoStockRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.create(request));
    }

    @GetMapping("/movimientos/{id}")
    public ResponseEntity<MovimientoStockResponse> findById(@PathVariable String id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/movimientos")
    public ResponseEntity<List<MovimientoStockResponse>> findAll() {
        return ResponseEntity.ok(queryService.findAll());
    }

    @GetMapping("/movimientos/producto/{productoId}")
    public ResponseEntity<List<MovimientoStockResponse>> findByProducto(@PathVariable String productoId) {
        return ResponseEntity.ok(queryService.findByProductoId(productoId));
    }

    @GetMapping("/movimientos/tenant/{tenantId}")
    public ResponseEntity<List<MovimientoStockResponse>> findByTenant(@PathVariable String tenantId) {
        return ResponseEntity.ok(queryService.findByTenantId(tenantId));
    }

    @DeleteMapping("/movimientos/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        commandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
