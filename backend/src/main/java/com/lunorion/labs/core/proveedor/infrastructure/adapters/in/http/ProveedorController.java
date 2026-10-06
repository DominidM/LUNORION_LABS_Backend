package com.lunorion.labs.core.proveedor.infrastructure.adapters.in.http;

import com.lunorion.labs.core.proveedor.application.dto.in.CreateProveedorRequest;
import com.lunorion.labs.core.proveedor.application.dto.out.ProveedorResponse;
import com.lunorion.labs.core.proveedor.domain.filter.ProveedorFiltro;
import com.lunorion.labs.core.proveedor.domain.ports.in.IProveedorCommandPort;
import com.lunorion.labs.core.proveedor.domain.ports.in.IProveedorQueryPort;
import com.lunorion.labs.shared.application.dto.out.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@Tag(name = "Proveedores", description = "Gestión de proveedores")
public class ProveedorController {

    private final IProveedorCommandPort commandService;
    private final IProveedorQueryPort queryService;

    public ProveedorController(IProveedorCommandPort commandService, IProveedorQueryPort queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Crear proveedor", description = "Registra un nuevo proveedor en el sistema")
    public ResponseEntity<ProveedorResponse> create(@RequestBody CreateProveedorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.create(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proveedor por ID", description = "Retorna un proveedor por su ID")
    public ResponseEntity<ProveedorResponse> findById(@PathVariable String id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar proveedores", description = "Retorna los proveedores de forma paginada, con búsqueda y filtros")
    public ResponseEntity<PagedResponse<ProveedorResponse>> findAll(
            @Parameter(description = "ID del tenant (opcional)") @RequestParam(required = false) String tenantId,
            @Parameter(description = "Búsqueda por razón social, RUC, contacto, teléfono, email o dirección") @RequestParam(required = false) String search,
            @Parameter(description = "Estado del proveedor", schema = @Schema(allowableValues = {"activo", "inactivo"})) @RequestParam(required = false) String estado,
            @Parameter(description = "Número de página (base 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Registros por página", schema = @Schema(type = "integer", allowableValues = {"5", "10", "25", "50"}, defaultValue = "10")) @RequestParam(defaultValue = "10") int size) {
        ProveedorFiltro filtro = new ProveedorFiltro(tenantId, search, estado, page, size);
        return ResponseEntity.ok(queryService.search(filtro));
    }

    @GetMapping("/tenant/{tenantId}")
    @Operation(summary = "Listar proveedores por tenant", description = "Retorna todos los proveedores de un tenant")
    public ResponseEntity<List<ProveedorResponse>> findByTenantId(@PathVariable String tenantId) {
        return ResponseEntity.ok(queryService.findByTenantId(tenantId));
    }

    @PostMapping("/{id}/desactivar")
    @Operation(summary = "Desactivar proveedor", description = "Desactiva un proveedor (soft delete)")
    public ResponseEntity<Void> desactivar(@PathVariable String id) {
        commandService.desactivar(id);
        return ResponseEntity.ok().build();
    }
}
