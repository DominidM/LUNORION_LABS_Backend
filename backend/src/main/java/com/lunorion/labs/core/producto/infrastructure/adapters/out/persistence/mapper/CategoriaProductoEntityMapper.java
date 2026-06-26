package com.lunorion.labs.core.producto.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.producto.domain.entity.CategoriaProducto;
import com.lunorion.labs.core.producto.infrastructure.adapters.out.persistence.entity.CategoriaProductoEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CategoriaProductoEntityMapper {

    public CategoriaProductoEntity toEntity(CategoriaProducto domain) {
        if (domain == null) return null;
        CategoriaProductoEntity entity = new CategoriaProductoEntity();
        entity.setId(domain.getId());
        entity.setTenantId(UUID.fromString(domain.getTenantId()));
        entity.setNombre(domain.getNombre());
        entity.setCategoriaPadreId(domain.getCategoriaPadreId());
        return entity;
    }

    public CategoriaProducto toDomain(CategoriaProductoEntity entity) {
        if (entity == null) return null;
        CategoriaProducto domain = new CategoriaProducto(entity.getId(),
                entity.getTenantId().toString(), entity.getNombre());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }
}
