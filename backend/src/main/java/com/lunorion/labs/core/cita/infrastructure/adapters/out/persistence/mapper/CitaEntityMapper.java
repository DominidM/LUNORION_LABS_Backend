package com.lunorion.labs.core.cita.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.cita.domain.entity.Cita;
import com.lunorion.labs.core.cita.infrastructure.adapters.out.persistence.entity.CitaEntity;
import org.springframework.stereotype.Component;

@Component
public class CitaEntityMapper {

    public CitaEntity toEntity(Cita domain) {
        if (domain == null) return null;
        CitaEntity entity = new CitaEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        entity.setClienteId(domain.getClienteId());
        entity.setVehiculoId(domain.getVehiculoId());
        entity.setTecnicoId(domain.getTecnicoId());
        entity.setServicioDescripcion(domain.getServicioDescripcion());
        entity.setFechaHora(domain.getFechaHora());
        entity.setDuracionMinutos(domain.getDuracionMinutos());
        entity.setEstado(domain.getEstado());
        entity.setRecordatorioEnviado(domain.isRecordatorioEnviado());
        entity.setNotificarWhatsapp(domain.isNotificarWhatsapp());
        entity.setUsuarioCreoId(domain.getUsuarioCreoId());
        return entity;
    }

    public Cita toDomain(CitaEntity entity) {
        if (entity == null) return null;
        Cita domain = new Cita(
            entity.getId(),
            entity.getTenantId().toString(),
            entity.getClienteId(),
            entity.getVehiculoId(),
            entity.getFechaHora(),
            entity.getDuracionMinutos(),
            entity.getUsuarioCreoId()
        );
        domain.setTecnicoId(entity.getTecnicoId());
        domain.setServicioDescripcion(entity.getServicioDescripcion());
        domain.setNotificarWhatsapp(entity.getNotificarWhatsapp() != null && entity.getNotificarWhatsapp());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }
}
