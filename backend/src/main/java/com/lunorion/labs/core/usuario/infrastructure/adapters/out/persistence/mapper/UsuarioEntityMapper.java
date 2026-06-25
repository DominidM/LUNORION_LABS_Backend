package com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.usuario.domain.entity.Usuario;
import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEntityMapper {

    public UsuarioEntity toEntity(Usuario domain) {
        if (domain == null) return null;
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        entity.setEmail(domain.getEmail());
        entity.setPasswordHash(domain.getPasswordHash());
        entity.setNombres(domain.getNombres());
        entity.setApellidos(domain.getApellidos());
        entity.setDni(domain.getDni());
        entity.setTelefono(domain.getTelefono());
        entity.setRol(domain.getRol());
        entity.setActivo(domain.isActivo());
        return entity;
    }

    public Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) return null;
        Usuario domain = new Usuario(entity.getId(), entity.getTenantId().toString(), entity.getEmail(),
                entity.getNombres(), entity.getApellidos(), entity.getRol());
        domain.setPasswordHash(entity.getPasswordHash());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }
}
