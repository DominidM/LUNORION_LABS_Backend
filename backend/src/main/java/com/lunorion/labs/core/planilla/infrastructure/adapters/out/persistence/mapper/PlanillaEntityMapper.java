package com.lunorion.labs.core.planilla.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.planilla.domain.entity.Asistencia;
import com.lunorion.labs.core.planilla.domain.entity.BoletaPago;
import com.lunorion.labs.core.planilla.domain.entity.TipoAsistencia;
import com.lunorion.labs.core.planilla.infrastructure.adapters.out.persistence.entity.AsistenciaEntity;
import com.lunorion.labs.core.planilla.infrastructure.adapters.out.persistence.entity.BoletaPagoEntity;
import org.springframework.stereotype.Component;

@Component
public class PlanillaEntityMapper {

    public AsistenciaEntity toEntity(Asistencia domain) {
        if (domain == null) return null;
        AsistenciaEntity entity = new AsistenciaEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        entity.setTecnicoId(java.util.UUID.fromString(domain.getTecnicoId()));
        entity.setFecha(domain.getFecha());
        entity.setHoraEntrada(domain.getHoraEntrada());
        entity.setHoraSalida(domain.getHoraSalida());
        entity.setHorasTrabajadas(domain.getHorasTrabajadas());
        entity.setTipo(domain.getTipo().name());
        return entity;
    }

    public Asistencia toDomain(AsistenciaEntity entity) {
        if (entity == null) return null;
        return new Asistencia(entity.getId(), entity.getTenantId().toString(),
                entity.getTecnicoId().toString(), entity.getFecha(),
                entity.getHoraEntrada(), entity.getHoraSalida(),
                TipoAsistencia.valueOf(entity.getTipo()));
    }

    public BoletaPagoEntity toEntity(BoletaPago domain) {
        if (domain == null) return null;
        BoletaPagoEntity entity = new BoletaPagoEntity();
        entity.setId(domain.getId());
        entity.setTenantId(java.util.UUID.fromString(domain.getTenantId()));
        entity.setTecnicoId(java.util.UUID.fromString(domain.getTecnicoId()));
        entity.setPeriodo(domain.getPeriodo());
        entity.setSueldoBasico(domain.getSueldoBasico());
        entity.setHorasExtras(domain.getHorasExtras());
        entity.setComisiones(domain.getComisiones());
        entity.setAsignacionFamiliar(domain.getAsignacionFamiliar());
        entity.setTotalIngresos(domain.getTotalIngresos());
        entity.setDescuentoOnp(domain.getDescuentoOnp());
        entity.setDescuentoAfp(domain.getDescuentoAfp());
        entity.setDescuentoOtros(domain.getDescuentoOtros());
        entity.setTotalDescuentos(domain.getTotalDescuentos());
        entity.setNetoPagar(domain.getNetoPagar());
        entity.setEssalud(domain.getEssalud());
        entity.setPdfGenerado(domain.getPdfGenerado());
        entity.setPdfFirmado(domain.getPdfFirmado());
        return entity;
    }

    public BoletaPago toDomain(BoletaPagoEntity entity) {
        if (entity == null) return null;
        BoletaPago domain = new BoletaPago(entity.getId(), entity.getTenantId().toString(),
                entity.getTecnicoId().toString(), entity.getPeriodo(),
                entity.getSueldoBasico(), entity.getHorasExtras(),
                entity.getComisiones(), entity.getAsignacionFamiliar());
        domain.setPdfGenerado(entity.getPdfGenerado());
        domain.setPdfFirmado(entity.getPdfFirmado());
        return domain;
    }
}
