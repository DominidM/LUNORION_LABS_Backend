package com.lunorion.labs.core.ordencompra.domain.ports.in;

import com.lunorion.labs.core.ordencompra.application.dto.out.OrdenCompraResponse;

import java.util.List;
import java.util.Optional;

public interface IOrdenCompraQueryPort {
    Optional<OrdenCompraResponse> findById(String id);
    List<OrdenCompraResponse> findByTenantId(String tenantId);
    List<OrdenCompraResponse> findByProveedorId(String proveedorId);
}
