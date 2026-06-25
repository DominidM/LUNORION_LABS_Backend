package com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.orden_trabajo.domain.entity.OrdenTrabajo;
import com.lunorion.labs.core.orden_trabajo.domain.entity.OtInsumo;
import com.lunorion.labs.core.orden_trabajo.domain.entity.OtManoObra;
import com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.entity.OrdenTrabajoEntity;
import com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.entity.OtInsumoEntity;
import com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.entity.OtManoObraEntity;
import org.springframework.stereotype.Component;

@Component
public class OrdenTrabajoEntityMapper {

    public OrdenTrabajoEntity toEntity(OrdenTrabajo domain) {
        if (domain == null) return null;
        OrdenTrabajoEntity entity = new OrdenTrabajoEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        if (domain.getClienteId() != null) entity.setClienteId(java.util.UUID.fromString(domain.getClienteId()));
        if (domain.getVehiculoId() != null) entity.setVehiculoId(java.util.UUID.fromString(domain.getVehiculoId()));
        if (domain.getTecnicoId() != null) entity.setTecnicoId(java.util.UUID.fromString(domain.getTecnicoId()));
        entity.setNumeroOt(domain.getNumeroOt());
        entity.setEstado(domain.getEstado());
        entity.setMotivoIngreso(domain.getMotivoIngreso());
        entity.setKilometrajeIngreso(domain.getKilometrajeIngreso());
        entity.setFechaPrometida(domain.getFechaPrometida());
        entity.setTotalRepuestos(domain.getTotalRepuestos());
        entity.setTotalManoObra(domain.getTotalManoObra());
        entity.setTotal(domain.getTotal());
        if (domain.getUsuarioCreoId() != null) entity.setUsuarioCreoId(java.util.UUID.fromString(domain.getUsuarioCreoId()));
        return entity;
    }

    public OrdenTrabajo toDomain(OrdenTrabajoEntity entity) {
        if (entity == null) return null;
        OrdenTrabajo domain = new OrdenTrabajo(entity.getId(),
                entity.getTenantId().toString(),
                entity.getClienteId() != null ? entity.getClienteId().toString() : null,
                entity.getVehiculoId() != null ? entity.getVehiculoId().toString() : null,
                entity.getTecnicoId() != null ? entity.getTecnicoId().toString() : null,
                entity.getNumeroOt(),
                entity.getEstado(),
                entity.getMotivoIngreso(),
                entity.getUsuarioCreoId() != null ? entity.getUsuarioCreoId().toString() : null);
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    public OtInsumoEntity toInsumoEntity(OtInsumo domain) {
        if (domain == null) return null;
        OtInsumoEntity entity = new OtInsumoEntity();
        entity.setId(domain.getId());
        entity.setOrdenTrabajoId(java.util.UUID.fromString(domain.getOrdenTrabajoId()));
        entity.setProductoId(java.util.UUID.fromString(domain.getProductoId()));
        entity.setCantidad(domain.getCantidad());
        entity.setPrecioUnitario(domain.getPrecioUnitario());
        entity.setSubtotal(domain.getSubtotal());
        return entity;
    }

    public OtInsumo toInsumoDomain(OtInsumoEntity entity) {
        if (entity == null) return null;
        return new OtInsumo(entity.getId(),
                entity.getOrdenTrabajoId().toString(),
                entity.getProductoId().toString(),
                entity.getCantidad(),
                entity.getPrecioUnitario(),
                entity.getSubtotal());
    }

    public OtManoObraEntity toManoObraEntity(OtManoObra domain) {
        if (domain == null) return null;
        OtManoObraEntity entity = new OtManoObraEntity();
        entity.setId(domain.getId());
        entity.setOrdenTrabajoId(java.util.UUID.fromString(domain.getOrdenTrabajoId()));
        if (domain.getTecnicoId() != null) entity.setTecnicoId(java.util.UUID.fromString(domain.getTecnicoId()));
        entity.setServicioDescripcion(domain.getServicioDescripcion());
        entity.setHoras(domain.getHoras());
        entity.setTarifaHora(domain.getTarifaHora());
        entity.setSubtotal(domain.getSubtotal());
        return entity;
    }

    public OtManoObra toManoObraDomain(OtManoObraEntity entity) {
        if (entity == null) return null;
        return new OtManoObra(entity.getId(),
                entity.getOrdenTrabajoId().toString(),
                entity.getTecnicoId() != null ? entity.getTecnicoId().toString() : null,
                entity.getServicioDescripcion(),
                entity.getHoras(),
                entity.getTarifaHora(),
                entity.getSubtotal());
    }
}
