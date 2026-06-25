package com.lunorion.labs.core.proveedor.domain.ports.in;

import com.lunorion.labs.core.proveedor.application.dto.in.CreateProveedorRequest;
import com.lunorion.labs.core.proveedor.application.dto.out.ProveedorResponse;

public interface IProveedorCommandPort {
    ProveedorResponse create(CreateProveedorRequest request);
    void desactivar(String id);
}
