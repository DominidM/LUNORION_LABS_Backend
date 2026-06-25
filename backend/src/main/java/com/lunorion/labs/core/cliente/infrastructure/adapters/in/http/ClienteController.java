package com.lunorion.labs.core.cliente.infrastructure.adapters.in.http;

import com.lunorion.labs.core.cliente.application.dto.in.CreateClienteRequest;
import com.lunorion.labs.core.cliente.application.dto.out.ClienteResponse;
import com.lunorion.labs.core.cliente.application.dto.out.HistorialCompraResponse;
import com.lunorion.labs.core.cliente.application.dto.out.HistorialTrabajoResponse;
import com.lunorion.labs.core.cliente.application.dto.out.RentabilidadClienteResponse;
import com.lunorion.labs.core.cliente.domain.ports.in.IClienteCommandPort;
import com.lunorion.labs.core.cliente.domain.ports.in.IClienteQueryPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Gestión de clientes")
public class ClienteController {

    private final IClienteCommandPort commandService;
    private final IClienteQueryPort queryService;

    public ClienteController(IClienteCommandPort commandService, IClienteQueryPort queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Crear cliente", description = "Registra un nuevo cliente en el sistema")
    public ResponseEntity<ClienteResponse> create(@RequestBody CreateClienteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente", description = "Actualiza los datos de un cliente existente")
    public ResponseEntity<ClienteResponse> update(@PathVariable String id, @RequestBody CreateClienteRequest request) {
        return ResponseEntity.ok(commandService.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Retorna un cliente por su ID")
    public ResponseEntity<ClienteResponse> findById(@PathVariable String id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar clientes", description = "Retorna todos los clientes registrados")
    public ResponseEntity<List<ClienteResponse>> findAll() {
        return ResponseEntity.ok(queryService.findAll());
    }

    @GetMapping("/documento/{numero}")
    @Operation(summary = "Buscar cliente por documento", description = "Retorna un cliente por su número de documento")
    public ResponseEntity<ClienteResponse> findByNumeroDocumento(@PathVariable String numero) {
        return queryService.findByNumeroDocumento(numero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tenant/{tenantId}")
    @Operation(summary = "Listar clientes por tenant", description = "Retorna todos los clientes de un tenant")
    public ResponseEntity<List<ClienteResponse>> findByTenantId(@PathVariable String tenantId) {
        return ResponseEntity.ok(queryService.findByTenantId(tenantId));
    }

    @PostMapping("/{id}/desactivar")
    @Operation(summary = "Desactivar cliente", description = "Desactiva un cliente (soft delete)")
    public ResponseEntity<Void> desactivar(@PathVariable String id) {
        commandService.desactivar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/historial-trabajos")
    @Operation(summary = "Historial de trabajos", description = "Retorna el historial de órdenes de trabajo de un cliente")
    public ResponseEntity<List<HistorialTrabajoResponse>> workHistory(@PathVariable String id) {
        return ResponseEntity.ok(queryService.workHistory(id));
    }

    @GetMapping("/{id}/historial-compras")
    @Operation(summary = "Historial de compras", description = "Retorna el historial de compras/ventas de un cliente")
    public ResponseEntity<List<HistorialCompraResponse>> purchaseHistory(@PathVariable String id) {
        return ResponseEntity.ok(queryService.purchaseHistory(id));
    }

    @GetMapping("/{id}/rentabilidad")
    @Operation(summary = "Rentabilidad del cliente", description = "Retorna indicadores de rentabilidad de un cliente")
    public ResponseEntity<RentabilidadClienteResponse> profitability(@PathVariable String id) {
        return ResponseEntity.ok(queryService.profitability(id));
    }
}
