package com.lunorion.labs.core.tecnico.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.tecnico.domain.entity.ConfiguracionComision;
import com.lunorion.labs.core.tecnico.domain.entity.Tecnico;
import com.lunorion.labs.core.tecnico.domain.entity.TipoComision;
import com.lunorion.labs.core.tecnico.infrastructure.adapters.out.persistence.entity.ConfiguracionComisionEntity;
import com.lunorion.labs.core.tecnico.infrastructure.adapters.out.persistence.entity.TecnicoEntity;
import org.springframework.stereotype.Component;

@Component
public class TecnicoEntityMapper {

    public TecnicoEntity toEntity(Tecnico domain) {
        if (domain == null) return null;
        TecnicoEntity entity = new TecnicoEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        if (domain.getUsuarioId() != null) entity.setUsuarioId(java.util.UUID.fromString(domain.getUsuarioId()));
        entity.setEspecialidades(domain.getEspecialidades());
        entity.setTarifaHora(domain.getTarifaHora());
        entity.setNumeroContacto(domain.getNumeroContacto());
        entity.setFechaIngreso(domain.getFechaIngreso());
        entity.setActivo(domain.isActivo());
        return entity;
    }

    public Tecnico toDomain(TecnicoEntity entity) {
        if (entity == null) return null;
        Tecnico domain = new Tecnico(entity.getId(), entity.getTenantId().toString(),
                entity.getUsuarioId() != null ? entity.getUsuarioId().toString() : null,
                entity.getEspecialidades(), entity.getTarifaHora(), entity.getNumeroContacto());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    public ConfiguracionComisionEntity toComisionEntity(ConfiguracionComision domain) {
        if (domain == null) return null;
        ConfiguracionComisionEntity entity = new ConfiguracionComisionEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        entity.setTecnicoId(java.util.UUID.fromString(domain.getTecnicoId()));
        if (domain.getProductoId() != null) entity.setProductoId(java.util.UUID.fromString(domain.getProductoId()));
        entity.setTipo(domain.getTipo().name());
        entity.setPorcentaje(domain.getPorcentaje());
        entity.setActivo(domain.isActivo());
        return entity;
    }

    public ConfiguracionComision toComisionDomain(ConfiguracionComisionEntity entity) {
        if (entity == null) return null;
        return new ConfiguracionComision(entity.getId(), entity.getTenantId().toString(),
                entity.getTecnicoId().toString(),
                entity.getProductoId() != null ? entity.getProductoId().toString() : null,
                TipoComision.valueOf(entity.getTipo()), entity.getPorcentaje());
    }
}
