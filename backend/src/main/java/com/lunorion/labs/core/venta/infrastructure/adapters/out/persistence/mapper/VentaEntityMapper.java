package com.lunorion.labs.core.venta.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.venta.domain.entity.Venta;
import com.lunorion.labs.core.venta.domain.entity.VentaItem;
import com.lunorion.labs.core.venta.infrastructure.adapters.out.persistence.entity.VentaEntity;
import com.lunorion.labs.core.venta.infrastructure.adapters.out.persistence.entity.VentaItemEntity;
import org.springframework.stereotype.Component;

@Component
public class VentaEntityMapper {

    public VentaEntity toEntity(Venta domain) {
        if (domain == null) return null;
        VentaEntity entity = new VentaEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        if (domain.getClienteId() != null) entity.setClienteId(java.util.UUID.fromString(domain.getClienteId()));
        entity.setTipo(domain.getTipo());
        entity.setSubtotal(domain.getSubtotal());
        entity.setIgv(domain.getIgv());
        entity.setTotal(domain.getTotal());
        entity.setDescuento(domain.getDescuento());
        entity.setMetodoPago(domain.getMetodoPago());
        entity.setEstadoPago(domain.getEstadoPago());
        if (domain.getUsuarioId() != null) entity.setUsuarioId(java.util.UUID.fromString(domain.getUsuarioId()));
        return entity;
    }

    public Venta toDomain(VentaEntity entity) {
        if (entity == null) return null;
        Venta domain = new Venta(entity.getId(),
                entity.getTenantId().toString(),
                entity.getClienteId() != null ? entity.getClienteId().toString() : null,
                entity.getTipo(),
                entity.getSubtotal(),
                entity.getIgv(),
                entity.getTotal(),
                entity.getUsuarioId() != null ? entity.getUsuarioId().toString() : null);
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    public VentaItemEntity toItemEntity(VentaItem domain) {
        if (domain == null) return null;
        VentaItemEntity entity = new VentaItemEntity();
        entity.setId(domain.getId());
        entity.setVentaId(java.util.UUID.fromString(domain.getVentaId()));
        entity.setProductoId(java.util.UUID.fromString(domain.getProductoId()));
        entity.setCantidad(domain.getCantidad());
        entity.setPrecioUnitario(domain.getPrecioUnitario());
        entity.setDescuento(domain.getDescuento());
        entity.setSubtotal(domain.getSubtotal());
        return entity;
    }

    public VentaItem toItemDomain(VentaItemEntity entity) {
        if (entity == null) return null;
        return new VentaItem(entity.getId(),
                entity.getVentaId().toString(),
                entity.getProductoId().toString(),
                entity.getCantidad(),
                entity.getPrecioUnitario(),
                entity.getDescuento(),
                entity.getSubtotal());
    }
}
