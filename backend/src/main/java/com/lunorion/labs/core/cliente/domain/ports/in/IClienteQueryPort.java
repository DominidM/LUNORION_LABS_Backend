package com.lunorion.labs.core.cliente.domain.ports.in;

import com.lunorion.labs.core.cliente.application.dto.out.ClienteResponse;

import java.util.List;
import java.util.Optional;

public interface IClienteQueryPort {
    Optional<ClienteResponse> findById(String id);
    Optional<ClienteResponse> findByNumeroDocumento(String numeroDocumento);
    List<ClienteResponse> findByTenantId(String tenantId);
    List<ClienteResponse> findAll();
}
