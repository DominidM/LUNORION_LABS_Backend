package com.lunorion.labs.core.proveedor.application.mapper;

import com.lunorion.labs.core.proveedor.application.dto.in.CreateProveedorRequest;
import com.lunorion.labs.core.proveedor.application.dto.out.ProveedorResponse;
import com.lunorion.labs.core.proveedor.domain.entity.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public Proveedor toDomain(CreateProveedorRequest request) {
        return Proveedor.create(
            request.getTenantId(),
            request.getRuc(),
            request.getRazonSocial(),
            request.getContacto(),
            request.getTelefono(),
            request.getEmail(),
            request.getDireccion(),
            request.getCondicionesPago()
        );
    }

    public ProveedorResponse toResponse(Proveedor proveedor) {
        ProveedorResponse response = new ProveedorResponse();
        response.setId(proveedor.getId().toString());
        response.setRuc(proveedor.getRuc());
        response.setRazonSocial(proveedor.getRazonSocial());
        response.setContacto(proveedor.getContacto());
        response.setTelefono(proveedor.getTelefono());
        response.setEmail(proveedor.getEmail());
        response.setDireccion(proveedor.getDireccion());
        response.setCondicionesPago(proveedor.getCondicionesPago());
        response.setActivo(proveedor.isActivo());
        return response;
    }
}
