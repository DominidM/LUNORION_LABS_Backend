package com.lunorion.labs.core.usuario.infrastructure.adapters.in.http;

import com.lunorion.labs.core.usuario.application.dto.in.AsignarPermisosRequest;
import com.lunorion.labs.core.usuario.application.dto.in.CreateUsuarioRequest;
import com.lunorion.labs.core.usuario.application.dto.out.PermisoResponse;
import com.lunorion.labs.core.usuario.application.dto.out.UsuarioResponse;
import com.lunorion.labs.core.usuario.domain.filter.UsuarioFiltro;
import com.lunorion.labs.core.usuario.domain.ports.in.IUsuarioCommandPort;
import com.lunorion.labs.core.usuario.domain.ports.in.IUsuarioQueryPort;
import com.lunorion.labs.shared.application.dto.out.PagedResponse;
import com.lunorion.labs.shared.application.export.ReportExporter;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioCommandPort commandService;
    private final IUsuarioQueryPort queryService;
    private final ReportExporter reportExporter;

    public UsuarioController(IUsuarioCommandPort commandService, IUsuarioQueryPort queryService,
                             ReportExporter reportExporter) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.reportExporter = reportExporter;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@RequestBody CreateUsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.create(request));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<UsuarioResponse>> findAll(
            @Parameter(description = "ID del tenant (opcional)") @RequestParam(required = false) String tenantId,
            @Parameter(description = "Búsqueda por nombre, apellido, DNI, email o teléfono") @RequestParam(required = false) String search,
            @Parameter(description = "Estado del empleado", schema = @Schema(allowableValues = {"activo", "inactivo"})) @RequestParam(required = false) String estado,
            @Parameter(description = "Rol/cargo del empleado") @RequestParam(required = false) String rol,
            @Parameter(description = "Número de página (base 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Registros por página", schema = @Schema(type = "integer", allowableValues = {"5", "10", "25", "50"}, defaultValue = "10")) @RequestParam(defaultValue = "10") int size) {
        UsuarioFiltro filtro = new UsuarioFiltro(tenantId, search, estado, rol, page, size);
        return ResponseEntity.ok(queryService.search(filtro));
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> export(
            @Parameter(description = "Formato del archivo", schema = @Schema(allowableValues = {"XLSX", "PDF"}, defaultValue = "XLSX")) @RequestParam(defaultValue = "XLSX") String formato,
            @Parameter(description = "ID del tenant (opcional)") @RequestParam(required = false) String tenantId,
            @Parameter(description = "Búsqueda por nombre, apellido, DNI, email o teléfono") @RequestParam(required = false) String search,
            @Parameter(description = "Estado del empleado", schema = @Schema(allowableValues = {"activo", "inactivo"})) @RequestParam(required = false) String estado,
            @Parameter(description = "Rol/cargo del empleado") @RequestParam(required = false) String rol) {
        UsuarioFiltro filtro = new UsuarioFiltro(tenantId, search, estado, rol, 0, 0);
        List<UsuarioResponse> data = queryService.searchAll(filtro);
        List<String> headers = List.of("Empleado", "DNI", "Cargo", "Email", "Teléfono", "Estado");
        List<List<String>> rows = data.stream()
                .map(u -> List.of(
                        ((u.getNombres() == null ? "" : u.getNombres()) + " "
                                + (u.getApellidos() == null ? "" : u.getApellidos())).trim(),
                        u.getDni() == null ? "" : u.getDni(),
                        u.getRol() == null ? "" : u.getRol(),
                        u.getEmail() == null ? "" : u.getEmail(),
                        u.getTelefono() == null ? "" : u.getTelefono(),
                        u.isActivo() ? "Activo" : "Inactivo"))
                .collect(Collectors.toList());
        byte[] body = "PDF".equalsIgnoreCase(formato)
                ? reportExporter.toPdf("Reporte de Empleados", headers, rows)
                : reportExporter.toXlsx("Empleados", headers, rows);
        return reportExporter.respond(body, formato, "reporte_empleados");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable String id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<UsuarioResponse>> findByTenant(@PathVariable String tenantId) {
        return ResponseEntity.ok(queryService.findByTenantId(tenantId));
    }

    @PostMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivar(@PathVariable String id) {
        commandService.desactivar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/activar")
    public ResponseEntity<Void> activar(@PathVariable String id) {
        commandService.activar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/permisos")
    public ResponseEntity<Void> asignarPermisos(@PathVariable String id, @RequestBody AsignarPermisosRequest request) {
        commandService.asignarPermisos(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/permisos")
    public ResponseEntity<List<PermisoResponse>> listarPermisos(@RequestParam String tenantId) {
        return ResponseEntity.ok(queryService.listarPermisos(tenantId));
    }
}
