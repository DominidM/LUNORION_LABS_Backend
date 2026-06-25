package com.lunorion.labs.core.inventario.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.inventario.domain.entity.MovimientoStock;
import com.lunorion.labs.core.inventario.infrastructure.adapters.out.persistence.entity.MovimientoStockEntity;
import org.springframework.stereotype.Component;

@Component
public class MovimientoStockEntityMapper {

    public MovimientoStockEntity toEntity(MovimientoStock domain) {
        if (domain == null) return null;
        MovimientoStockEntity entity = new MovimientoStockEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        entity.setProductoId(java.util.UUID.fromString(domain.getProductoId()));
        entity.setTipo(domain.getTipo());
        entity.setSubtipo(domain.getSubtipo());
        entity.setCantidad(domain.getCantidad());
        entity.setCostoUnitario(domain.getCostoUnitario());
        entity.setStockAnterior(domain.getStockAnterior());
        entity.setStockPosterior(domain.getStockPosterior());
        entity.setDocumentoOrigen(domain.getDocumentoOrigen());
        entity.setObservacion(domain.getObservacion());
        if (domain.getUsuarioId() != null) entity.setUsuarioId(java.util.UUID.fromString(domain.getUsuarioId()));
        return entity;
    }

    public MovimientoStock toDomain(MovimientoStockEntity entity) {
        if (entity == null) return null;
        MovimientoStock domain = new MovimientoStock(entity.getId(),
                entity.getTenantId().toString(),
                entity.getProductoId().toString(),
                entity.getTipo(),
                entity.getSubtipo(),
                entity.getCantidad(),
                entity.getCostoUnitario(),
                entity.getStockAnterior(),
                entity.getStockPosterior(),
                entity.getDocumentoOrigen(),
                entity.getObservacion(),
                entity.getUsuarioId() != null ? entity.getUsuarioId().toString() : null);
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }
}
