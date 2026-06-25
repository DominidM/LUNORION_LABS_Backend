package com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.comprobante_electronico.domain.entity.ResumenDiario;
import com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.entity.ResumenDiarioEntity;
import org.springframework.stereotype.Component;

@Component
public class ResumenDiarioEntityMapper {

    public ResumenDiarioEntity toEntity(ResumenDiario domain) {
        if (domain == null) return null;
        ResumenDiarioEntity entity = new ResumenDiarioEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        entity.setFechaResumen(domain.getFechaResumen());
        entity.setCodigoResumen(domain.getCodigoResumen());
        entity.setXmlFirmado(domain.getXmlFirmado());
        entity.setXmlCdr(domain.getXmlCdr());
        entity.setEstado(domain.getEstado());
        entity.setTotalBoletas(domain.getTotalBoletas());
        entity.setTotalAnulaciones(domain.getTotalAnulaciones());
        return entity;
    }

    public ResumenDiario toDomain(ResumenDiarioEntity entity) {
        if (entity == null) return null;
        ResumenDiario domain = new ResumenDiario(
            entity.getId(),
            entity.getTenantId().toString(),
            entity.getFechaResumen(),
            entity.getCodigoResumen()
        );
        domain.setXmlFirmado(entity.getXmlFirmado());
        domain.setXmlCdr(entity.getXmlCdr());
        domain.setTotalBoletas(entity.getTotalBoletas());
        domain.setTotalAnulaciones(entity.getTotalAnulaciones());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }
}
