package com.lunorion.labs.core.cita.infrastructure.adapters.in.http;

import com.lunorion.labs.core.cita.application.dto.in.CreateCitaRequest;
import com.lunorion.labs.core.cita.application.dto.out.CitaResponse;
import com.lunorion.labs.core.cita.domain.ports.in.ICitaCommandPort;
import com.lunorion.labs.core.cita.domain.ports.in.ICitaQueryPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponse> findById(@PathVariable String id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<CitaResponse>> findByTenant(@PathVariable String tenantId) {
        return ResponseEntity.ok(queryService.findByTenantId(tenantId));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CitaResponse>> findByCliente(@PathVariable String clienteId) {
        return ResponseEntity.ok(queryService.findByClienteId(clienteId));
    }

    @GetMapping("/tecnico/{tecnicoId}")
    public ResponseEntity<List<CitaResponse>> findByTecnico(@PathVariable String tecnicoId) {
        return ResponseEntity.ok(queryService.findByTecnicoId(tecnicoId));
    }

    @PostMapping("/{id}/confirmar")
    public ResponseEntity<Void> confirmar(@PathVariable String id) {
        commandService.confirmar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable String id) {
        commandService.cancelar(id);
        return ResponseEntity.ok().build();
    }
}
