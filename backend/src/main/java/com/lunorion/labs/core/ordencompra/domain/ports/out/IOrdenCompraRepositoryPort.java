package com.lunorion.labs.core.ordencompra.domain.ports.out;

import com.lunorion.labs.core.ordencompra.domain.entity.OrdenCompra;

import java.util.List;
import java.util.Optional;

public interface IOrdenCompraRepositoryPort {
    OrdenCompra save(OrdenCompra ordenCompra);
    Optional<OrdenCompra> findById(String id);
    List<OrdenCompra> findByTenantId(String tenantId);
    List<OrdenCompra> findByProveedorId(String proveedorId);
    void deleteById(String id);
}
