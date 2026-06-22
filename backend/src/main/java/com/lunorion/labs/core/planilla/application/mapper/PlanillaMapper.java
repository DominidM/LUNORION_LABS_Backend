package com.lunorion.labs.core.planilla.application.mapper;

import com.lunorion.labs.core.planilla.application.dto.in.RegistrarAsistenciaRequest;
import com.lunorion.labs.core.planilla.application.dto.out.AsistenciaResponse;
import com.lunorion.labs.core.planilla.application.dto.out.BoletaPagoResponse;
import com.lunorion.labs.core.planilla.domain.entity.Asistencia;
import com.lunorion.labs.core.planilla.domain.entity.BoletaPago;
import com.lunorion.labs.core.planilla.domain.entity.TipoAsistencia;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PlanillaMapper {

    public Asistencia toDomain(RegistrarAsistenciaRequest request) {
        return Asistencia.registrar(
            request.getTenantId(),
            request.getTecnicoId(),
            request.getFecha(),
            request.getHoraEntrada(),
            request.getHoraSalida(),
            request.getTipo() != null ? TipoAsistencia.valueOf(request.getTipo()) : TipoAsistencia.NORMAL
        );
    }

    public BoletaPago toDomain(String tenantId, String tecnicoId, String periodo,
                                BigDecimal sueldoBasico, BigDecimal horasExtras,
                                BigDecimal comisiones, BigDecimal asignacionFamiliar) {
        return BoletaPago.generar(tenantId, tecnicoId, periodo, sueldoBasico, horasExtras, comisiones, asignacionFamiliar);
    }

    public AsistenciaResponse toResponse(Asistencia asistencia) {
        AsistenciaResponse response = new AsistenciaResponse();
        response.setId(asistencia.getId().toString());
        response.setTenantId(asistencia.getTenantId());
        response.setTecnicoId(asistencia.getTecnicoId());
        response.setFecha(asistencia.getFecha());
        response.setHoraEntrada(asistencia.getHoraEntrada());
        response.setHoraSalida(asistencia.getHoraSalida());
        response.setHorasTrabajadas(asistencia.getHorasTrabajadas());
        response.setTipo(asistencia.getTipo().name());
        return response;
    }

    public BoletaPagoResponse toResponse(BoletaPago boleta) {
        BoletaPagoResponse response = new BoletaPagoResponse();
        response.setId(boleta.getId().toString());
        response.setTenantId(boleta.getTenantId());
        response.setTecnicoId(boleta.getTecnicoId());
        response.setPeriodo(boleta.getPeriodo());
        response.setSueldoBasico(boleta.getSueldoBasico());
        response.setHorasExtras(boleta.getHorasExtras());
        response.setComisiones(boleta.getComisiones());
        response.setAsignacionFamiliar(boleta.getAsignacionFamiliar());
        response.setTotalIngresos(boleta.getTotalIngresos());
        response.setDescuentoOnp(boleta.getDescuentoOnp());
        response.setDescuentoAfp(boleta.getDescuentoAfp());
        response.setDescuentoOtros(boleta.getDescuentoOtros());
        response.setTotalDescuentos(boleta.getTotalDescuentos());
        response.setNetoPagar(boleta.getNetoPagar());
        response.setEssalud(boleta.getEssalud());
        response.setPdfGenerado(boleta.getPdfGenerado());
        response.setPdfFirmado(boleta.getPdfFirmado());
        return response;
    }
}
