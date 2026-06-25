package com.lunorion.labs.core.ordencompra.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.ordencompra.domain.entity.OrdenCompra;
import com.lunorion.labs.core.ordencompra.domain.entity.OrdenCompraItem;
import com.lunorion.labs.core.ordencompra.infrastructure.adapters.out.persistence.entity.OrdenCompraEntity;
import com.lunorion.labs.core.ordencompra.infrastructure.adapters.out.persistence.entity.OrdenCompraItemEntity;
import org.springframework.stereotype.Component;

@Component
public class OrdenCompraEntityMapper {

    public OrdenCompraEntity toEntity(OrdenCompra domain) {
        if (domain == null) return null;
        OrdenCompraEntity entity = new OrdenCompraEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        entity.setProveedorId(java.util.UUID.fromString(domain.getProveedorId()));
        entity.setNumeroOrden(domain.getNumeroOrden());
        entity.setFechaEmision(domain.getFechaEmision());
        entity.setEstado(domain.getEstado());
        entity.setTotal(domain.getTotal());
        entity.setObservacion(domain.getObservacion());
        entity.setUsuarioId(java.util.UUID.fromString(domain.getUsuarioId()));
        return entity;
    }

    public OrdenCompra toDomain(OrdenCompraEntity entity) {
        if (entity == null) return null;
        OrdenCompra domain = new OrdenCompra(
            entity.getId(),
            entity.getTenantId().toString(),
            entity.getProveedorId().toString(),
            entity.getNumeroOrden(),
            entity.getFechaEmision(),
            entity.getEstado(),
            entity.getTotal(),
            entity.getObservacion(),
            entity.getUsuarioId().toString()
        );
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }

    public OrdenCompraItemEntity toItemEntity(OrdenCompraItem domain) {
        if (domain == null) return null;
        OrdenCompraItemEntity entity = new OrdenCompraItemEntity();
        entity.setId(domain.getId());
        entity.setOrdenCompraId(java.util.UUID.fromString(domain.getOrdenCompraId()));
        entity.setProductoId(java.util.UUID.fromString(domain.getProductoId()));
        entity.setCantidad(domain.getCantidad());
        entity.setCantidadRecibida(domain.getCantidadRecibida());
        entity.setPrecioUnitario(domain.getPrecioUnitario());
        entity.setSubtotal(domain.getSubtotal());
        return entity;
    }

    public OrdenCompraItem toItemDomain(OrdenCompraItemEntity entity) {
        if (entity == null) return null;
        OrdenCompraItem domain = new OrdenCompraItem(
            entity.getId(),
            entity.getOrdenCompraId().toString(),
            entity.getProductoId().toString(),
            entity.getCantidad(),
            entity.getCantidadRecibida(),
            entity.getPrecioUnitario(),
            entity.getSubtotal()
        );
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }
}
