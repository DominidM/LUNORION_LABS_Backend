package com.lunorion.labs.core.proveedor.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.proveedor.domain.entity.Proveedor;
import com.lunorion.labs.core.proveedor.infrastructure.adapters.out.persistence.entity.ProveedorEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProveedorEntityMapper {

    public ProveedorEntity toEntity(Proveedor domain) {
        if (domain == null) return null;
        ProveedorEntity entity = new ProveedorEntity();
        entity.setId(domain.getId());
        entity.setTenantId(UUID.fromString(domain.getTenantId()));
        entity.setRuc(domain.getRuc());
        entity.setRazonSocial(domain.getRazonSocial());
        entity.setContacto(domain.getContacto());
        entity.setTelefono(domain.getTelefono());
        entity.setEmail(domain.getEmail());
        entity.setDireccion(domain.getDireccion());
        entity.setCondicionesPago(domain.getCondicionesPago());
        entity.setActivo(domain.isActivo());
        return entity;
    }

    public Proveedor toDomain(ProveedorEntity entity) {
        if (entity == null) return null;
        Proveedor domain = new Proveedor(entity.getId(), entity.getTenantId().toString(),
                entity.getRuc(), entity.getRazonSocial());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }
}
