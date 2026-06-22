package com.lunorion.labs.core.cita.application.mapper;

import com.lunorion.labs.core.cita.application.dto.in.CreateCitaRequest;
import com.lunorion.labs.core.cita.application.dto.out.CitaResponse;
import com.lunorion.labs.core.cita.domain.entity.Cita;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CitaMapper {

    public Cita toDomain(CreateCitaRequest request) {
        return Cita.create(
            request.getTenantId(),
            request.getClienteId(),
            request.getVehiculoId(),
            request.getTecnicoId(),
            request.getServicioDescripcion(),
            LocalDateTime.parse(request.getFechaHora()),
            request.getDuracionMinutos(),
            request.isNotificarWhatsapp(),
            request.getUsuarioCreoId()
        );
    }

    public CitaResponse toResponse(Cita domain) {
        CitaResponse response = new CitaResponse();
        response.setId(domain.getId().toString());
        response.setTenantId(domain.getTenantId());
        response.setClienteId(domain.getClienteId());
        response.setVehiculoId(domain.getVehiculoId());
        response.setTecnicoId(domain.getTecnicoId());
        response.setServicioDescripcion(domain.getServicioDescripcion());
        response.setFechaHora(domain.getFechaHora() != null ? domain.getFechaHora().toString() : null);
        response.setDuracionMinutos(domain.getDuracionMinutos());
        response.setEstado(domain.getEstado());
        response.setRecordatorioEnviado(domain.isRecordatorioEnviado());
        response.setNotificarWhatsapp(domain.isNotificarWhatsapp());
        response.setUsuarioCreoId(domain.getUsuarioCreoId());
        return response;
    }
}
