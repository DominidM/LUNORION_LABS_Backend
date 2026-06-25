package com.lunorion.labs.core.proveedor.domain.ports.out;

import com.lunorion.labs.core.proveedor.domain.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IProveedorRepositoryPort {
    Proveedor save(Proveedor proveedor);
    Optional<Proveedor> findById(String id);
    List<Proveedor> findByTenantId(String tenantId);
    List<Proveedor> findAll();
}
