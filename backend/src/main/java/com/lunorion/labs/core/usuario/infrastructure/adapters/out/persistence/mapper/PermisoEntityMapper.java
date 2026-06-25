package com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.usuario.domain.entity.Permiso;
import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.entity.PermisoEntity;
import org.springframework.stereotype.Component;

@Component
public class PermisoEntityMapper {

    public PermisoEntity toEntity(Permiso domain) {
        if (domain == null) return null;
        PermisoEntity entity = new PermisoEntity();
        entity.setId(domain.getId());
        entity.setCodigo(domain.getCodigo());
        entity.setNombre(domain.getNombre());
        entity.setModulo(domain.getModulo());
        return entity;
    }

    public Permiso toDomain(PermisoEntity entity) {
        if (entity == null) return null;
        return new Permiso(entity.getId(), entity.getCodigo(), entity.getNombre(), entity.getModulo());
    }
}
