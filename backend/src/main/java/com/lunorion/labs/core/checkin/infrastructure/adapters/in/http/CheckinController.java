package com.lunorion.labs.core.checkin.infrastructure.adapters.in.http;

import com.lunorion.labs.core.checkin.application.dto.in.CreateCheckinRequest;
import com.lunorion.labs.core.checkin.application.dto.out.CheckinResponse;
import com.lunorion.labs.core.checkin.domain.ports.in.ICheckinCommandPort;
import com.lunorion.labs.core.checkin.domain.ports.in.ICheckinQueryPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkin")
public class CheckinController {

    private final ICheckinCommandPort commandService;
    private final ICheckinQueryPort queryService;

    public CheckinController(ICheckinCommandPort commandService, ICheckinQueryPort queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<CheckinResponse> create(@RequestBody CreateCheckinRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckinResponse> findById(@PathVariable String id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<CheckinResponse>> findByTenant(@PathVariable String tenantId) {
        return ResponseEntity.ok(queryService.findByTenantId(tenantId));
    }

    @GetMapping("/ot/{otId}")
    public ResponseEntity<CheckinResponse> findByOtId(@PathVariable String otId) {
        return queryService.findByOtId(otId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/asignar-ot")
    public ResponseEntity<Void> asignarOt(@PathVariable String id, @RequestParam String otId) {
        commandService.asignarOt(id, otId);
        return ResponseEntity.ok().build();
    }
}
