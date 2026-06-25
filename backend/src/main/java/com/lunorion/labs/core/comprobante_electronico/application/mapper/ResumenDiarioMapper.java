package com.lunorion.labs.core.comprobante_electronico.application.mapper;

import com.lunorion.labs.core.comprobante_electronico.application.dto.in.CreateResumenDiarioRequest;
import com.lunorion.labs.core.comprobante_electronico.application.dto.out.ResumenDiarioResponse;
import com.lunorion.labs.core.comprobante_electronico.domain.entity.ResumenDiario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ResumenDiarioMapper {

    public ResumenDiario toDomain(CreateResumenDiarioRequest request) {
        ResumenDiario domain = ResumenDiario.create(
            request.getTenantId(),
            LocalDate.parse(request.getFechaResumen()),
            request.getCodigoResumen()
        );
        domain.setTotalBoletas(request.getTotalBoletas());
        domain.setTotalAnulaciones(request.getTotalAnulaciones());
        return domain;
    }

    public ResumenDiarioResponse toResponse(ResumenDiario domain) {
        ResumenDiarioResponse response = new ResumenDiarioResponse();
        response.setId(domain.getId().toString());
        response.setTenantId(domain.getTenantId());
        response.setFechaResumen(domain.getFechaResumen() != null ? domain.getFechaResumen().toString() : null);
        response.setCodigoResumen(domain.getCodigoResumen());
        response.setEstado(domain.getEstado());
        response.setTotalBoletas(domain.getTotalBoletas());
        response.setTotalAnulaciones(domain.getTotalAnulaciones());
        return response;
    }
}
