package com.lunorion.labs.core.cita.application.mapper;

import com.lunorion.labs.core.cita.application.dto.in.CreateCitaRequest;
import com.lunorion.labs.core.cita.application.dto.out.CitaResponse;
import com.lunorion.labs.core.cita.domain.entity.Cita;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CitaMapper {

    public Cita toDomain(CreateCitaRequest request) {
        return Cita.create(
            request.getTenantId(),
            UUID.fromString(request.getClienteId()),
            UUID.fromString(request.getVehiculoId()),
            UUID.fromString(request.getTecnicoId()),
            request.getServicioDescripcion(),
            LocalDateTime.parse(request.getFechaHora()),
            request.getDuracionMinutos(),
            request.isNotificarWhatsapp(),
            UUID.fromString(request.getUsuarioCreoId())
        );
    }

    public CitaResponse toResponse(Cita domain) {
        CitaResponse response = new CitaResponse();
        response.setId(domain.getId().toString());
        response.setTenantId(domain.getTenantId());
        response.setClienteId(domain.getClienteId().toString());
        response.setVehiculoId(domain.getVehiculoId().toString());
        response.setTecnicoId(domain.getTecnicoId().toString());
        response.setServicioDescripcion(domain.getServicioDescripcion());
        response.setFechaHora(domain.getFechaHora() != null ? domain.getFechaHora().toString() : null);
        response.setDuracionMinutos(domain.getDuracionMinutos());
        response.setEstado(domain.getEstado());
        response.setRecordatorioEnviado(domain.isRecordatorioEnviado());
        response.setNotificarWhatsapp(domain.isNotificarWhatsapp());
        response.setUsuarioCreoId(domain.getUsuarioCreoId().toString());
        return response;
    }
}
