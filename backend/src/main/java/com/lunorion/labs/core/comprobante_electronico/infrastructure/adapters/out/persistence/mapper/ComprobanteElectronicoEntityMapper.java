package com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.comprobante_electronico.domain.entity.ComprobanteElectronico;
import com.lunorion.labs.core.comprobante_electronico.domain.entity.ResumenDiario;
import com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.entity.ComprobanteElectronicoEntity;
import com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.entity.ResumenDiarioEntity;
import org.springframework.stereotype.Component;

@Component
public class ComprobanteElectronicoEntityMapper {

    public ComprobanteElectronicoEntity toEntity(ComprobanteElectronico domain) {
        if (domain == null) return null;
        ComprobanteElectronicoEntity entity = new ComprobanteElectronicoEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        entity.setVentaId(domain.getVentaId());
        entity.setTipo(domain.getTipo());
        entity.setSerie(domain.getSerie());
        entity.setNumero(domain.getNumero());
        entity.setFechaEmision(domain.getFechaEmision());
        entity.setHoraEmision(domain.getHoraEmision());
        entity.setXmlFirmado(domain.getXmlFirmado());
        entity.setXmlCdr(domain.getXmlCdr());
        entity.setHashFirma(domain.getHashFirma());
        entity.setEstadoSunat(domain.getEstadoSunat());
        entity.setCodigoErrorSunat(domain.getCodigoErrorSunat());
        entity.setDescripcionError(domain.getDescripcionError());
        entity.setComprobanteReferenciaId(domain.getComprobanteReferenciaId());
        entity.setMontoOperacionesGravadas(domain.getMontoOperacionesGravadas());
        entity.setMontoIgv(domain.getMontoIgv());
        entity.setMontoTotal(domain.getMontoTotal());
        entity.setRucCliente(domain.getRucCliente());
        entity.setRazonSocialCliente(domain.getRazonSocialCliente());
        entity.setIntentosEnvio(domain.getIntentosEnvio());
        entity.setUltimoEnvio(domain.getUltimoEnvio());
        entity.setEnviadoPorId(domain.getEnviadoPorId());
        return entity;
    }

    public ComprobanteElectronico toDomain(ComprobanteElectronicoEntity entity) {
        if (entity == null) return null;
        ComprobanteElectronico domain = new ComprobanteElectronico(
            entity.getId(),
            entity.getTenantId().toString(),
            entity.getVentaId(),
            entity.getTipo(),
            entity.getSerie(),
            entity.getNumero(),
            entity.getFechaEmision(),
            entity.getHoraEmision(),
            entity.getMontoTotal(),
            entity.getRucCliente(),
            entity.getRazonSocialCliente(),
            entity.getEnviadoPorId()
        );
        domain.setXmlFirmado(entity.getXmlFirmado());
        domain.setXmlCdr(entity.getXmlCdr());
        domain.setHashFirma(entity.getHashFirma());
        domain.setCodigoErrorSunat(entity.getCodigoErrorSunat());
        domain.setDescripcionError(entity.getDescripcionError());
        domain.setComprobanteReferenciaId(entity.getComprobanteReferenciaId());
        domain.setMontoOperacionesGravadas(entity.getMontoOperacionesGravadas());
        domain.setMontoIgv(entity.getMontoIgv());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }

    public ResumenDiarioEntity toResumenDiarioEntity(ResumenDiario domain) {
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

    public ResumenDiario toResumenDiarioDomain(ResumenDiarioEntity entity) {
        if (entity == null) return null;
        ResumenDiario domain = new ResumenDiario(entity.getId(), entity.getTenantId().toString(),
                entity.getFechaResumen(), entity.getCodigoResumen());
        if (entity.getXmlFirmado() != null) domain.setXmlFirmado(entity.getXmlFirmado());
        if (entity.getXmlCdr() != null) domain.setXmlCdr(entity.getXmlCdr());
        domain.setTotalBoletas(entity.getTotalBoletas());
        domain.setTotalAnulaciones(entity.getTotalAnulaciones());
        return domain;
    }
}
