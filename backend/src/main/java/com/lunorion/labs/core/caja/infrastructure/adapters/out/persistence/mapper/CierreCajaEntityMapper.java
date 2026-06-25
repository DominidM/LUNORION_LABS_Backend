package com.lunorion.labs.core.caja.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.caja.domain.entity.CierreCaja;
import com.lunorion.labs.core.caja.infrastructure.adapters.out.persistence.entity.CierreCajaEntity;
import org.springframework.stereotype.Component;

@Component
public class CierreCajaEntityMapper {

    public CierreCajaEntity toEntity(CierreCaja domain) {
        if (domain == null) return null;
        CierreCajaEntity entity = new CierreCajaEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        entity.setFecha(domain.getFecha());
        entity.setHoraApertura(domain.getHoraApertura());
        entity.setHoraCierre(domain.getHoraCierre());
        entity.setSaldoInicial(domain.getSaldoInicial());
        entity.setTotalIngresos(domain.getTotalIngresos());
        entity.setTotalEgresos(domain.getTotalEgresos());
        entity.setSaldoEsperado(domain.getSaldoEsperado());
        entity.setSaldoReal(domain.getSaldoReal());
        entity.setDescuadre(domain.getDescuadre());
        entity.setObservacion(domain.getObservacion());
        entity.setUsuarioAperturaId(domain.getUsuarioAperturaId());
        entity.setUsuarioCierreId(domain.getUsuarioCierreId());
        return entity;
    }

    public CierreCaja toDomain(CierreCajaEntity entity) {
        if (entity == null) return null;
        CierreCaja domain = new CierreCaja(
            entity.getId(),
            entity.getTenantId().toString(),
            entity.getFecha(),
            entity.getHoraApertura(),
            entity.getSaldoInicial(),
            entity.getUsuarioAperturaId()
        );
        domain.setTotalIngresos(entity.getTotalIngresos());
        domain.setTotalEgresos(entity.getTotalEgresos());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }
}
