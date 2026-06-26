package com.lunorion.labs.core.cotizacion.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.cotizacion.domain.entity.Cotizacion;
import com.lunorion.labs.core.cotizacion.domain.entity.CotizacionItem;
import com.lunorion.labs.core.cotizacion.infrastructure.adapters.out.persistence.entity.CotizacionEntity;
import com.lunorion.labs.core.cotizacion.infrastructure.adapters.out.persistence.entity.CotizacionItemEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CotizacionEntityMapper {

    public CotizacionEntity toEntity(Cotizacion domain) {
        if (domain == null) return null;
        CotizacionEntity entity = new CotizacionEntity();
        entity.setId(domain.getId());
        entity.setTenantId(UUID.fromString(domain.getTenantId()));
        entity.setClienteId(UUID.fromString(domain.getClienteId()));
        entity.setVehiculoId(domain.getVehiculoId() != null ? UUID.fromString(domain.getVehiculoId()) : null);
        entity.setFechaEmision(domain.getFechaEmision());
        entity.setFechaValidez(domain.getFechaValidez());
        entity.setEstado(domain.getEstado());
        entity.setSubtotal(domain.getSubtotal());
        entity.setIgv(domain.getIgv());
        entity.setTotal(domain.getTotal());
        entity.setNotas(domain.getNotas());
        entity.setActivo(domain.isActivo());
        if (domain.getItems() != null) {
            List<CotizacionItemEntity> itemEntities = domain.getItems().stream()
                    .map(item -> toItemEntity(item, entity))
                    .collect(Collectors.toList());
            entity.setItems(itemEntities);
        }
        return entity;
    }

    public Cotizacion toDomain(CotizacionEntity entity) {
        if (entity == null) return null;
        Cotizacion domain = new Cotizacion(entity.getId(),
                entity.getTenantId().toString(),
                entity.getClienteId().toString(),
                entity.getVehiculoId() != null ? entity.getVehiculoId().toString() : null,
                entity.getFechaEmision(),
                entity.getFechaValidez(),
                entity.getNotas());
        domain.setEstado(entity.getEstado());
        domain.setSubtotal(entity.getSubtotal());
        domain.setIgv(entity.getIgv());
        domain.setTotal(entity.getTotal());
        domain.setActivo(entity.getActivo());
        domain.setCreatedAt(entity.getCreatedAt());
        if (entity.getItems() != null) {
            domain.setItems(entity.getItems().stream()
                    .map(this::toItemDomain)
                    .collect(Collectors.toList()));
        } else {
            domain.setItems(Collections.emptyList());
        }
        return domain;
    }

    private CotizacionItemEntity toItemEntity(CotizacionItem item, CotizacionEntity parent) {
        CotizacionItemEntity entity = new CotizacionItemEntity();
        entity.setId(item.getId());
        entity.setCotizacionId(parent.getId());
        entity.setDescripcion(item.getDescripcion());
        entity.setCantidad(item.getCantidad());
        entity.setPrecioUnitario(item.getPrecioUnitario());
        entity.setSubtotal(item.getSubtotal());
        entity.setCotizacion(parent);
        return entity;
    }

    private CotizacionItem toItemDomain(CotizacionItemEntity entity) {
        return new CotizacionItem(
                entity.getId(),
                entity.getCotizacionId().toString(),
                entity.getDescripcion(),
                entity.getCantidad(),
                entity.getPrecioUnitario(),
                entity.getSubtotal()
        );
    }

    public List<CotizacionItem> toItemDomainList(List<CotizacionItemEntity> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream().map(this::toItemDomain).collect(Collectors.toList());
    }
}
