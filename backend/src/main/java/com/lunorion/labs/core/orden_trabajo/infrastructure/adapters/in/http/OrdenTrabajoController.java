package com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.in.http;

import com.lunorion.labs.core.orden_trabajo.application.dto.in.*;
import com.lunorion.labs.core.orden_trabajo.application.dto.out.CierreOtResponse;
import com.lunorion.labs.core.orden_trabajo.application.dto.out.KanbanResponse;
import com.lunorion.labs.core.orden_trabajo.application.dto.out.OrdenTrabajoResponse;
import com.lunorion.labs.core.orden_trabajo.domain.filter.OrdenTrabajoFiltro;
import com.lunorion.labs.core.orden_trabajo.domain.ports.in.IOrdenTrabajoCommandPort;
import com.lunorion.labs.core.orden_trabajo.domain.ports.in.IOrdenTrabajoQueryPort;
import com.lunorion.labs.shared.application.dto.out.PagedResponse;
import com.lunorion.labs.shared.application.export.ReportExporter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ordenes-trabajo")
public class OrdenTrabajoController {

    private final IOrdenTrabajoCommandPort commandService;
    private final IOrdenTrabajoQueryPort queryService;
    private final ReportExporter reportExporter;

    public OrdenTrabajoController(IOrdenTrabajoCommandPort commandService, IOrdenTrabajoQueryPort queryService,
                                  ReportExporter reportExporter) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.reportExporter = reportExporter;
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
    public ResponseEntity<PagedResponse<OrdenTrabajoResponse>> findAll(
            @Parameter(description = "ID del tenant (opcional)") @RequestParam(required = false) String tenantId,
            @Parameter(description = "Búsqueda por N° de OT o motivo de ingreso") @RequestParam(required = false) String search,
            @Parameter(description = "Estado de la orden", schema = @Schema(allowableValues = {"PENDIENTE", "EN_PROCESO", "EN_REPARACION", "CERRADO"})) @RequestParam(required = false) String estado,
            @Parameter(description = "Número de página (base 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Registros por página", schema = @Schema(type = "integer", allowableValues = {"5", "10", "25", "50"}, defaultValue = "10")) @RequestParam(defaultValue = "10") int size) {
        OrdenTrabajoFiltro filtro = new OrdenTrabajoFiltro(tenantId, search, estado, page, size);
        return ResponseEntity.ok(queryService.search(filtro));
    }

    @GetMapping(value = "/export/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Exportar órdenes de trabajo a PDF", description = "Exporta el listado filtrado en formato PDF")
    public ResponseEntity<byte[]> exportPdf(
            @Parameter(description = "ID del tenant (opcional)") @RequestParam(required = false) String tenantId,
            @Parameter(description = "Búsqueda por N° de OT o motivo de ingreso") @RequestParam(required = false) String search,
            @Parameter(description = "Estado de la orden", schema = @Schema(allowableValues = {"PENDIENTE", "EN_PROCESO", "EN_REPARACION", "CERRADO"})) @RequestParam(required = false) String estado) {
        byte[] body = reportExporter.toPdf("Reporte de Órdenes de Trabajo", EXPORT_HEADERS,
                buildExportRows(tenantId, search, estado));
        return reportExporter.pdfResponse(body, "reporte_ordenes_trabajo");
    }

    @GetMapping(value = "/export/excel", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @Operation(summary = "Exportar órdenes de trabajo a Excel", description = "Exporta el listado filtrado en formato Excel (XLSX)")
    public ResponseEntity<byte[]> exportExcel(
            @Parameter(description = "ID del tenant (opcional)") @RequestParam(required = false) String tenantId,
            @Parameter(description = "Búsqueda por N° de OT o motivo de ingreso") @RequestParam(required = false) String search,
            @Parameter(description = "Estado de la orden", schema = @Schema(allowableValues = {"PENDIENTE", "EN_PROCESO", "EN_REPARACION", "CERRADO"})) @RequestParam(required = false) String estado) {
        byte[] body = reportExporter.toXlsx("OrdenesTrabajo", EXPORT_HEADERS,
                buildExportRows(tenantId, search, estado));
        return reportExporter.xlsxResponse(body, "reporte_ordenes_trabajo");
    }

    private static final List<String> EXPORT_HEADERS =
            List.of("N° OT", "Estado", "Motivo de ingreso", "Fecha prometida", "Total");

    private List<List<String>> buildExportRows(String tenantId, String search, String estado) {
        OrdenTrabajoFiltro filtro = new OrdenTrabajoFiltro(tenantId, search, estado, 0, 0);
        return queryService.searchAll(filtro).stream()
                .map(ot -> List.of(
                        ot.getNumeroOt() == null ? "" : ot.getNumeroOt(),
                        ot.getEstado() == null ? "" : ot.getEstado(),
                        ot.getMotivoIngreso() == null ? "" : ot.getMotivoIngreso(),
                        ot.getFechaPrometida() == null ? "" : ot.getFechaPrometida().toString(),
                        ot.getTotal() == null ? "" : ot.getTotal().toPlainString()))
                .collect(Collectors.toList());
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

    @PutMapping("/{id}")
    public ResponseEntity<OrdenTrabajoResponse> update(@PathVariable String id, @RequestBody CreateOrdenTrabajoRequest request) {
        return ResponseEntity.ok(commandService.update(id, request));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<OrdenTrabajoResponse> changeStatus(@PathVariable String id, @RequestBody CambioEstadoRequest request) {
        return ResponseEntity.ok(commandService.cambiarEstado(id, request));
    }

    @PostMapping("/{id}/insumos")
    public ResponseEntity<OrdenTrabajoResponse> addInsumo(@PathVariable String id, @RequestBody AddInsumoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.addInsumo(id, request));
    }

    @DeleteMapping("/{id}/insumos/{insumoId}")
    public ResponseEntity<Void> removeInsumo(@PathVariable String id, @PathVariable String insumoId) {
        commandService.removeInsumo(id, insumoId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/labor")
    public ResponseEntity<OrdenTrabajoResponse> addLabor(@PathVariable String id, @RequestBody RegistroLaborRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.addLabor(id, request));
    }

    @PutMapping("/{id}/labor/{laborId}")
    public ResponseEntity<OrdenTrabajoResponse> updateLabor(@PathVariable String id, @PathVariable String laborId,
                                                            @RequestBody RegistroLaborRequest request) {
        return ResponseEntity.ok(commandService.updateLabor(id, laborId, request));
    }

    @PostMapping("/{id}/cerrar")
    public ResponseEntity<CierreOtResponse> close(@PathVariable String id) {
        return ResponseEntity.ok(commandService.close(id));
    }

    @PostMapping("/{id}/reabrir")
    public ResponseEntity<OrdenTrabajoResponse> reopen(@PathVariable String id) {
        return ResponseEntity.ok(commandService.reopen(id));
    }

    @GetMapping("/kanban")
    public ResponseEntity<List<KanbanResponse>> kanban(@RequestParam String tenantId) {
        return ResponseEntity.ok(queryService.kanban(tenantId));
    }
}
