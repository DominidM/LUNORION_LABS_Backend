package com.lunorion.labs.core.tenant.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.tenant.domain.entity.Tenant;
import com.lunorion.labs.core.tenant.infrastructure.adapters.out.persistence.entity.TenantEntity;
import org.springframework.stereotype.Component;

@Component
public class TenantEntityMapper {

    public TenantEntity toEntity(Tenant domain) {
        if (domain == null) return null;
        TenantEntity entity = new TenantEntity();
        entity.setId(domain.getId());
        entity.setRuc(domain.getRuc());
        entity.setRazonSocial(domain.getRazonSocial());
        entity.setNombreComercial(domain.getNombreComercial());
        entity.setDomicilioFiscal(domain.getDomicilioFiscal());
        entity.setEmail(domain.getEmail());
        entity.setTelefono(domain.getTelefono());
        entity.setRegimenTributario(domain.getRegimenTributario());
        entity.setLogoUrl(domain.getLogoUrl());
        entity.setColorPrimario(domain.getColorPrimario());
        entity.setColorSecundario(domain.getColorSecundario());
        entity.setPlan(domain.getPlan());
        entity.setEstado(domain.getEstado());
        entity.setCertificadoP12(domain.getCertificadoP12());
        entity.setCertificadoPassword(domain.getCertificadoPassword());
        entity.setCertificadoValidez(domain.getCertificadoValidez());
        entity.setRucValidadoSunat(domain.getRucValidadoSunat());
        return entity;
    }

    public Tenant toDomain(TenantEntity entity) {
        if (entity == null) return null;
        Tenant domain = new Tenant(entity.getId(), entity.getRuc(), entity.getRazonSocial());
        domain.setNombreComercial(entity.getNombreComercial());
        domain.setDomicilioFiscal(entity.getDomicilioFiscal());
        domain.setEmail(entity.getEmail());
        domain.setTelefono(entity.getTelefono());
        domain.setRegimenTributario(entity.getRegimenTributario());
        domain.setLogoUrl(entity.getLogoUrl());
        domain.setColorPrimario(entity.getColorPrimario());
        domain.setColorSecundario(entity.getColorSecundario());
        domain.setPlan(entity.getPlan());
        domain.setEstado(entity.getEstado());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }
}
