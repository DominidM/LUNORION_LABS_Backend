package com.lunorion.labs.core.vehiculo.infrastructure.adapters.in.http;

import com.lunorion.labs.core.vehiculo.application.dto.in.CreateVehiculoRequest;
import com.lunorion.labs.core.vehiculo.application.dto.out.VehiculoResponse;
import com.lunorion.labs.core.vehiculo.domain.ports.in.IVehiculoCommandPort;
import com.lunorion.labs.core.vehiculo.domain.ports.in.IVehiculoQueryPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehiculos")
@Tag(name = "Vehículos", description = "Gestión de vehículos")
public class VehiculoController {

    private final IVehiculoCommandPort commandService;
    private final IVehiculoQueryPort queryService;

    public VehiculoController(IVehiculoCommandPort commandService, IVehiculoQueryPort queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Crear vehículo", description = "Registra un nuevo vehículo en el sistema")
    public ResponseEntity<VehiculoResponse> create(@RequestBody CreateVehiculoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.create(request));
    }

    @GetMapping
    @Operation(summary = "Listar vehículos", description = "Retorna todos los vehículos registrados")
    public ResponseEntity<List<VehiculoResponse>> findAll() {
        return ResponseEntity.ok(queryService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener vehículo por ID", description = "Retorna un vehículo por su ID")
    public ResponseEntity<VehiculoResponse> findById(@PathVariable String id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tenant/{tenantId}")
    @Operation(summary = "Listar vehículos por tenant", description = "Retorna todos los vehículos de un tenant")
    public ResponseEntity<List<VehiculoResponse>> findByTenantId(@PathVariable String tenantId) {
        return ResponseEntity.ok(queryService.findByTenantId(tenantId));
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Listar vehículos por cliente", description = "Retorna los vehículos asignados a un cliente")
    public ResponseEntity<List<VehiculoResponse>> findByClienteId(@PathVariable String clienteId) {
        return ResponseEntity.ok(queryService.findByClienteId(clienteId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar vehículo", description = "Actualiza los datos de un vehículo existente")
    public ResponseEntity<VehiculoResponse> update(@PathVariable String id, @RequestBody CreateVehiculoRequest request) {
        return ResponseEntity.ok(commandService.update(id, request));
    }

    @PostMapping("/{id}/asignar-cliente")
    @Operation(summary = "Asignar cliente a vehículo", description = "Asigna un cliente existente a un vehículo")
    public ResponseEntity<Void> asignarCliente(@PathVariable String id, @RequestBody Map<String, String> body) {
        commandService.asignarCliente(id, body.get("clienteId"));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/desactivar")
    @Operation(summary = "Desactivar vehículo", description = "Desactiva un vehículo (soft delete)")
    public ResponseEntity<Void> desactivar(@PathVariable String id) {
        commandService.desactivar(id);
        return ResponseEntity.ok().build();
    }
}
