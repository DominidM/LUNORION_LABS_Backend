package com.lunorion.labs.core.cita.infrastructure.adapters.in.http;

import com.lunorion.labs.core.cita.application.dto.in.CambiarEstadoRequest;
import com.lunorion.labs.core.cita.application.dto.in.CreateCitaRequest;
import com.lunorion.labs.core.cita.application.dto.in.NotificacionesConfigRequest;
import com.lunorion.labs.core.cita.application.dto.in.ReprogramarCitaRequest;
import com.lunorion.labs.core.cita.application.dto.out.CitaResponse;
import com.lunorion.labs.core.cita.application.dto.out.DisponibilidadResponse;
import com.lunorion.labs.core.cita.application.dto.out.NotificacionesConfigResponse;
import com.lunorion.labs.core.cita.domain.ports.in.ICitaCommandPort;
import com.lunorion.labs.core.cita.domain.ports.in.ICitaQueryPort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final ICitaCommandPort commandService;
    private final ICitaQueryPort queryService;

    public CitaController(ICitaCommandPort commandService, ICitaQueryPort queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<CitaResponse> crear(@RequestBody CreateCitaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaResponse> reprogramar(@PathVariable String id, @RequestBody ReprogramarCitaRequest request) {
        return ResponseEntity.ok(commandService.reprogramar(UUID.fromString(id), request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponse> findById(@PathVariable String id) {
        return queryService.findById(UUID.fromString(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<CitaResponse>> findByTenant(@PathVariable String tenantId) {
        return ResponseEntity.ok(queryService.findByTenantId(tenantId));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CitaResponse>> findByCliente(@PathVariable String clienteId) {
        return ResponseEntity.ok(queryService.findByClienteId(UUID.fromString(clienteId)));
    }

    @GetMapping("/tecnico/{tecnicoId}")
    public ResponseEntity<List<CitaResponse>> findByTecnico(@PathVariable String tecnicoId) {
        return ResponseEntity.ok(queryService.findByTecnicoId(UUID.fromString(tecnicoId)));
    }

    @PostMapping("/{id}/confirmar")
    public ResponseEntity<Void> confirmar(@PathVariable String id) {
        commandService.confirmar(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable String id) {
        commandService.cancelar(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(@PathVariable String id, @RequestBody CambiarEstadoRequest request) {
        commandService.cambiarEstado(UUID.fromString(id), request.getEstado());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/calendario")
    public ResponseEntity<List<CitaResponse>> calendario(
            @RequestParam String tenantId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return ResponseEntity.ok(queryService.calendario(tenantId, desde, hasta));
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<List<DisponibilidadResponse>> disponibilidad(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam String tecnicoId) {
        return ResponseEntity.ok(queryService.disponibilidad(fecha, UUID.fromString(tecnicoId)));
    }

    @GetMapping("/notificaciones-config")
    public ResponseEntity<NotificacionesConfigResponse> getNotificacionesConfig(@RequestParam String tenantId) {
        return ResponseEntity.ok(queryService.getNotificacionesConfig(tenantId));
    }

    @PutMapping("/notificaciones-config")
    public ResponseEntity<NotificacionesConfigResponse> updateNotificacionesConfig(@RequestBody NotificacionesConfigRequest request) {
        return ResponseEntity.ok(commandService.updateNotificacionesConfig(request));
    }
}
