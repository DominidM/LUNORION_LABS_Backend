package com.lunorion.labs.core.proveedor.domain.ports.in;

import com.lunorion.labs.core.proveedor.application.dto.out.ProveedorResponse;

import java.util.List;
import java.util.Optional;

public interface IProveedorQueryPort {
    Optional<ProveedorResponse> findById(String id);
    List<ProveedorResponse> findByTenantId(String tenantId);
    List<ProveedorResponse> findAll();
}
