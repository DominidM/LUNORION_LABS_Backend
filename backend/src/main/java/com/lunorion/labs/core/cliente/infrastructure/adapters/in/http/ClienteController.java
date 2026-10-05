package com.lunorion.labs.core.cliente.infrastructure.adapters.in.http;

import com.lunorion.labs.core.cliente.application.dto.in.CreateClienteRequest;
import com.lunorion.labs.core.cliente.application.dto.out.ClienteResponse;
import com.lunorion.labs.core.cliente.application.dto.out.HistorialCompraResponse;
import com.lunorion.labs.core.cliente.application.dto.out.HistorialTrabajoResponse;
import com.lunorion.labs.core.cliente.application.dto.out.RentabilidadClienteResponse;
import com.lunorion.labs.core.cliente.domain.filter.ClienteFiltro;
import com.lunorion.labs.core.cliente.domain.ports.in.IClienteCommandPort;
import com.lunorion.labs.core.cliente.domain.ports.in.IClienteQueryPort;
import com.lunorion.labs.shared.application.dto.out.PagedResponse;
import com.lunorion.labs.shared.application.export.ReportExporter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Gestión de clientes")
public class ClienteController {

    private final IClienteCommandPort commandService;
    private final IClienteQueryPort queryService;
    private final ReportExporter reportExporter;

    public ClienteController(IClienteCommandPort commandService, IClienteQueryPort queryService,
                             ReportExporter reportExporter) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.reportExporter = reportExporter;
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
    @Operation(summary = "Listar clientes", description = "Retorna los clientes de forma paginada, con búsqueda y filtros")
    public ResponseEntity<PagedResponse<ClienteResponse>> findAll(
            @Parameter(description = "ID del tenant (opcional)") @RequestParam(required = false) String tenantId,
            @Parameter(description = "Búsqueda por nombre, documento, teléfono, email o razón social") @RequestParam(required = false) String search,
            @Parameter(description = "Estado del cliente", schema = @Schema(allowableValues = {"activo", "inactivo"})) @RequestParam(required = false) String estado,
            @Parameter(description = "Tipo de documento", schema = @Schema(allowableValues = {"DNI", "RUC", "CE"})) @RequestParam(required = false) String tipoDocumento,
            @Parameter(description = "Número de página (base 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Registros por página", schema = @Schema(type = "integer", allowableValues = {"5", "10", "25", "50"}, defaultValue = "10")) @RequestParam(defaultValue = "10") int size) {
        ClienteFiltro filtro = new ClienteFiltro(tenantId, search, estado, tipoDocumento, page, size);
        return ResponseEntity.ok(queryService.search(filtro));
    }

    @GetMapping("/export")
    @Operation(summary = "Exportar clientes", description = "Exporta el listado filtrado en formato PDF o XLSX")
    public ResponseEntity<byte[]> export(
            @Parameter(description = "Formato del archivo", schema = @Schema(allowableValues = {"XLSX", "PDF"}, defaultValue = "XLSX")) @RequestParam(defaultValue = "XLSX") String formato,
            @Parameter(description = "ID del tenant (opcional)") @RequestParam(required = false) String tenantId,
            @Parameter(description = "Búsqueda por nombre, documento, teléfono, email o razón social") @RequestParam(required = false) String search,
            @Parameter(description = "Estado del cliente", schema = @Schema(allowableValues = {"activo", "inactivo"})) @RequestParam(required = false) String estado,
            @Parameter(description = "Tipo de documento", schema = @Schema(allowableValues = {"DNI", "RUC", "CE"})) @RequestParam(required = false) String tipoDocumento) {
        ClienteFiltro filtro = new ClienteFiltro(tenantId, search, estado, tipoDocumento, 0, 0);
        List<ClienteResponse> data = queryService.searchAll(filtro);
        List<String> headers = List.of("Cliente", "Documento", "Teléfono", "Email", "Estado");
        List<List<String>> rows = data.stream()
                .map(c -> List.of(
                        nombreCompleto(c),
                        ((c.getTipoDocumento() == null ? "" : c.getTipoDocumento() + " ")
                                + (c.getNumeroDocumento() == null ? "" : c.getNumeroDocumento())).trim(),
                        c.getTelefono() == null ? "" : c.getTelefono(),
                        c.getEmail() == null ? "" : c.getEmail(),
                        c.isActivo() ? "Activo" : "Inactivo"))
                .collect(Collectors.toList());
        byte[] body = "PDF".equalsIgnoreCase(formato)
                ? reportExporter.toPdf("Reporte de Clientes", headers, rows)
                : reportExporter.toXlsx("Clientes", headers, rows);
        return reportExporter.respond(body, formato, "reporte_clientes");
    }

    private String nombreCompleto(ClienteResponse c) {
        String nombres = c.getNombres() == null ? "" : c.getNombres();
        String apellidos = c.getApellidos() == null ? "" : c.getApellidos();
        return (nombres + " " + apellidos).trim();
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

    @PostMapping("/{id}/activar")
    @Operation(summary = "Activar cliente", description = "Reactiva un cliente previamente desactivado")
    public ResponseEntity<Void> activar(@PathVariable String id) {
        commandService.activar(id);
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
