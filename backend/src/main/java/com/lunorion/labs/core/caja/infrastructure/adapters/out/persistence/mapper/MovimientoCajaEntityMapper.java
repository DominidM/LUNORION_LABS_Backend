package com.lunorion.labs.core.caja.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.caja.domain.entity.MovimientoCaja;
import com.lunorion.labs.core.caja.infrastructure.adapters.out.persistence.entity.MovimientoCajaEntity;
import org.springframework.stereotype.Component;

@Component
public class MovimientoCajaEntityMapper {

    public MovimientoCajaEntity toEntity(MovimientoCaja domain) {
        if (domain == null) return null;
        MovimientoCajaEntity entity = new MovimientoCajaEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        entity.setCierreCajaId(java.util.UUID.fromString(domain.getCierreCajaId()));
        entity.setTipo(domain.getTipo());
        entity.setMetodoPago(domain.getMetodoPago());
        entity.setMonto(domain.getMonto());
        entity.setReferencia(domain.getReferencia());
        entity.setConcepto(domain.getConcepto());
        entity.setUsuarioId(domain.getUsuarioId());
        return entity;
    }

    public MovimientoCaja toDomain(MovimientoCajaEntity entity) {
        if (entity == null) return null;
        MovimientoCaja domain = new MovimientoCaja(
            entity.getId(),
            entity.getTenantId().toString(),
            entity.getCierreCajaId().toString(),
            entity.getTipo(),
            entity.getMetodoPago(),
            entity.getMonto(),
            entity.getConcepto(),
            entity.getUsuarioId()
        );
        domain.setReferencia(entity.getReferencia());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }
}
