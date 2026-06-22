package com.lunorion.labs.core.tenant.infrastructure.adapters.in.http;

import com.lunorion.labs.core.tenant.application.dto.in.CreateTenantRequest;
import com.lunorion.labs.core.tenant.application.dto.out.TenantResponse;
import com.lunorion.labs.core.tenant.domain.ports.in.ITenantCommandPort;
import com.lunorion.labs.core.tenant.domain.ports.in.ITenantQueryPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final ITenantCommandPort commandService;
    private final ITenantQueryPort queryService;

    public TenantController(ITenantCommandPort commandService, ITenantQueryPort queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<TenantResponse> create(@RequestBody CreateTenantRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantResponse> findById(@PathVariable String id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TenantResponse>> findAll() {
        return ResponseEntity.ok(queryService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody CreateTenantRequest request) {
        commandService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        commandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
