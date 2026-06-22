package com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.in.http;

import com.lunorion.labs.core.comprobante_electronico.application.dto.in.CreateComprobanteRequest;
import com.lunorion.labs.core.comprobante_electronico.application.dto.out.ComprobanteResponse;
import com.lunorion.labs.core.comprobante_electronico.domain.ports.in.IComprobanteCommandPort;
import com.lunorion.labs.core.comprobante_electronico.domain.ports.in.IComprobanteQueryPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteController {

    private final IComprobanteCommandPort commandService;
    private final IComprobanteQueryPort queryService;

    public ComprobanteController(IComprobanteCommandPort commandService, IComprobanteQueryPort queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<ComprobanteResponse> create(@RequestBody CreateComprobanteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComprobanteResponse> findById(@PathVariable String id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<ComprobanteResponse>> findByTenant(@PathVariable String tenantId) {
        return ResponseEntity.ok(queryService.findByTenantId(tenantId));
    }

    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<List<ComprobanteResponse>> findByVenta(@PathVariable String ventaId) {
        return ResponseEntity.ok(queryService.findByVentaId(ventaId));
    }

    @PostMapping("/{id}/firmar")
    public ResponseEntity<Void> firmar(@PathVariable String id) {
        commandService.firmar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/enviar")
    public ResponseEntity<Void> enviarSunat(@PathVariable String id) {
        commandService.enviarSunat(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/aceptar")
    public ResponseEntity<Void> aceptar(@PathVariable String id) {
        commandService.aceptar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/rechazar")
    public ResponseEntity<Void> rechazar(@PathVariable String id, @RequestParam String error) {
        commandService.rechazar(id, error);
        return ResponseEntity.ok().build();
    }
}
