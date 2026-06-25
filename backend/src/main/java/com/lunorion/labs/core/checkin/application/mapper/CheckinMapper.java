package com.lunorion.labs.core.checkin.application.mapper;

import com.lunorion.labs.core.checkin.application.dto.in.CreateCheckinRequest;
import com.lunorion.labs.core.checkin.application.dto.out.CheckinResponse;
import com.lunorion.labs.core.checkin.domain.entity.Checkin;
import org.springframework.stereotype.Component;

@Component
public class CheckinMapper {

    public Checkin toDomain(CreateCheckinRequest request) {
        return Checkin.create(
            request.getTenantId(),
            request.getClienteId(),
            request.getVehiculoId(),
            request.getKilometraje(),
            request.getNivelCombustible(),
            request.getObservacionesCliente(),
            request.getFirmaCliente(),
            request.getUsuarioId()
        );
    }

    public CheckinResponse toResponse(Checkin checkin) {
        CheckinResponse response = new CheckinResponse();
        response.setId(checkin.getId().toString());
        response.setTenantId(checkin.getTenantId());
        response.setClienteId(checkin.getClienteId());
        response.setVehiculoId(checkin.getVehiculoId());
        response.setKilometraje(checkin.getKilometraje());
        response.setNivelCombustible(checkin.getNivelCombustible());
        response.setObservacionesCliente(checkin.getObservacionesCliente());
        response.setFirmaCliente(checkin.getFirmaCliente());
        response.setPdfActa(checkin.getPdfActa());
        response.setOtId(checkin.getOtId());
        response.setUsuarioId(checkin.getUsuarioId());
        return response;
    }
}
