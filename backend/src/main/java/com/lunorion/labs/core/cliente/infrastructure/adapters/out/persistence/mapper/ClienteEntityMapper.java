package com.lunorion.labs.core.cliente.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.cliente.domain.entity.Cliente;
import com.lunorion.labs.core.cliente.infrastructure.adapters.out.persistence.entity.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClienteEntityMapper {

    public ClienteEntity toEntity(Cliente domain) {
        if (domain == null) return null;
        ClienteEntity entity = new ClienteEntity();
        entity.setId(domain.getId());
        entity.setTenantId(UUID.fromString(domain.getTenantId()));
        entity.setTipoDocumento(domain.getTipoDocumento());
        entity.setNumeroDocumento(domain.getNumeroDocumento());
        entity.setNombres(domain.getNombres());
        entity.setApellidos(domain.getApellidos());
        entity.setRazonSocial(domain.getRazonSocial());
        entity.setDireccion(domain.getDireccion());
        entity.setTelefono(domain.getTelefono());
        entity.setEmail(domain.getEmail());
        entity.setActivo(domain.isActivo());
        return entity;
    }

    public Cliente toDomain(ClienteEntity entity) {
        if (entity == null) return null;
        Cliente domain = new Cliente(entity.getId(), entity.getTenantId().toString(),
                entity.getTipoDocumento(), entity.getNumeroDocumento(),
                entity.getNombres(), entity.getApellidos(), entity.getRazonSocial());
        domain.setDireccion(entity.getDireccion());
        domain.setTelefono(entity.getTelefono());
        domain.setEmail(entity.getEmail());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }
}
