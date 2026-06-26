package com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.checkin.domain.entity.AnguloFoto;
import com.lunorion.labs.core.checkin.domain.entity.Checkin;
import com.lunorion.labs.core.checkin.domain.entity.CheckinFoto;
import com.lunorion.labs.core.checkin.domain.entity.Garantia;
import com.lunorion.labs.core.checkin.domain.entity.TipoFoto;
import com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.entity.CheckinEntity;
import com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.entity.CheckinFotoEntity;
import com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.entity.GarantiaEntity;
import org.springframework.stereotype.Component;

@Component
public class CheckinEntityMapper {

    public CheckinEntity toEntity(Checkin domain) {
        if (domain == null) return null;
        CheckinEntity entity = new CheckinEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        if (domain.getClienteId() != null) entity.setClienteId(java.util.UUID.fromString(domain.getClienteId()));
        if (domain.getVehiculoId() != null) entity.setVehiculoId(java.util.UUID.fromString(domain.getVehiculoId()));
        entity.setKilometraje(domain.getKilometraje());
        entity.setNivelCombustible(domain.getNivelCombustible());
        entity.setObservacionesCliente(domain.getObservacionesCliente());
        entity.setFirmaCliente(domain.getFirmaCliente());
        entity.setPdfActa(domain.getPdfActa());
        if (domain.getOtId() != null) entity.setOtId(java.util.UUID.fromString(domain.getOtId()));
        if (domain.getUsuarioId() != null) entity.setUsuarioId(java.util.UUID.fromString(domain.getUsuarioId()));
        return entity;
    }

    public Checkin toDomain(CheckinEntity entity) {
        if (entity == null) return null;
        Checkin domain = new Checkin(entity.getId(), entity.getTenantId().toString(),
                entity.getClienteId() != null ? entity.getClienteId().toString() : null,
                entity.getVehiculoId() != null ? entity.getVehiculoId().toString() : null,
                entity.getKilometraje(), entity.getNivelCombustible(),
                entity.getObservacionesCliente(), entity.getFirmaCliente(),
                entity.getUsuarioId() != null ? entity.getUsuarioId().toString() : null);
        if (entity.getOtId() != null) domain.asignarOt(entity.getOtId().toString());
        if (entity.getPdfActa() != null) domain.adjuntarActa(entity.getPdfActa());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }

    public CheckinFotoEntity toFotoEntity(CheckinFoto domain) {
        if (domain == null) return null;
        CheckinFotoEntity entity = new CheckinFotoEntity();
        entity.setId(domain.getId());
        entity.setCheckinId(java.util.UUID.fromString(domain.getCheckinId()));
        entity.setTipo(domain.getTipo().name());
        entity.setAngulo(domain.getAngulo().name());
        entity.setUrl(domain.getUrl());
        return entity;
    }

    public CheckinFoto toFotoDomain(CheckinFotoEntity entity) {
        if (entity == null) return null;
        return new CheckinFoto(entity.getId(), entity.getCheckinId().toString(),
                TipoFoto.valueOf(entity.getTipo()),
                AnguloFoto.valueOf(entity.getAngulo()), entity.getUrl());
    }

    public GarantiaEntity toGarantiaEntity(Garantia domain) {
        if (domain == null) return null;
        GarantiaEntity entity = new GarantiaEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        if (domain.getOtOriginalId() != null) entity.setOtOriginalId(java.util.UUID.fromString(domain.getOtOriginalId()));
        if (domain.getOtGarantiaId() != null) entity.setOtGarantiaId(java.util.UUID.fromString(domain.getOtGarantiaId()));
        entity.setMotivo(domain.getMotivo());
        entity.setCostoRepuestos(domain.getCostoRepuestos());
        entity.setCostoManoObra(domain.getCostoManoObra());
        entity.setCostoTotal(domain.getCostoTotal());
        if (domain.getUsuarioId() != null) entity.setUsuarioId(java.util.UUID.fromString(domain.getUsuarioId()));
        return entity;
    }

    public Garantia toGarantiaDomain(GarantiaEntity entity) {
        if (entity == null) return null;
        Garantia domain = new Garantia(entity.getId(), entity.getTenantId().toString(),
                entity.getOtOriginalId() != null ? entity.getOtOriginalId().toString() : null,
                entity.getMotivo(), entity.getCostoRepuestos(), entity.getCostoManoObra(),
                entity.getUsuarioId() != null ? entity.getUsuarioId().toString() : null);
        if (entity.getOtGarantiaId() != null) domain.asignarOtGarantia(entity.getOtGarantiaId().toString());
        return domain;
    }
}
