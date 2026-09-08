package com.lunorion.labs.core.comprobante_electronico.application.mapper;

import com.lunorion.labs.core.comprobante_electronico.application.dto.in.CreateComprobanteRequest;
import com.lunorion.labs.core.comprobante_electronico.application.dto.out.ComprobanteResponse;
import com.lunorion.labs.core.comprobante_electronico.domain.entity.ComprobanteElectronico;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Component
public class ComprobanteMapper {

    public ComprobanteElectronico toDomain(CreateComprobanteRequest request) {
        ComprobanteElectronico domain = ComprobanteElectronico.create(
            request.getTenantId(),
            UUID.fromString(request.getVentaId()),
            request.getTipo(),
            request.getSerie(),
            request.getNumero(),
            LocalDate.parse(request.getFechaEmision()),
            LocalTime.parse(request.getHoraEmision()),
            request.getMontoTotal(),
            request.getRucCliente(),
            request.getRazonSocialCliente(),
            UUID.fromString(request.getEnviadoPorId())
        );
        domain.setMontoOperacionesGravadas(request.getMontoOperacionesGravadas());
        domain.setMontoIgv(request.getMontoIgv());
        return domain;
    }

    public ComprobanteResponse toResponse(ComprobanteElectronico domain) {
        ComprobanteResponse response = new ComprobanteResponse();
        response.setId(domain.getId().toString());
        response.setTenantId(domain.getTenantId());
        response.setVentaId(domain.getVentaId().toString());
        response.setTipo(domain.getTipo());
        response.setSerie(domain.getSerie());
        response.setNumero(domain.getNumero());
        response.setFechaEmision(domain.getFechaEmision() != null ? domain.getFechaEmision().toString() : null);
        response.setHoraEmision(domain.getHoraEmision() != null ? domain.getHoraEmision().toString() : null);
        response.setXmlFirmado(domain.getXmlFirmado());
        response.setXmlCdr(domain.getXmlCdr());
        response.setEstadoSunat(domain.getEstadoSunat());
        response.setCodigoErrorSunat(domain.getCodigoErrorSunat());
        response.setDescripcionError(domain.getDescripcionError());
        response.setComprobanteReferenciaId(domain.getComprobanteReferenciaId() != null ? domain.getComprobanteReferenciaId().toString() : null);
        response.setMontoOperacionesGravadas(domain.getMontoOperacionesGravadas());
        response.setMontoIgv(domain.getMontoIgv());
        response.setMontoTotal(domain.getMontoTotal());
        response.setRucCliente(domain.getRucCliente());
        response.setRazonSocialCliente(domain.getRazonSocialCliente());
        response.setIntentosEnvio(domain.getIntentosEnvio());
        response.setUltimoEnvio(domain.getUltimoEnvio() != null ? domain.getUltimoEnvio().toString() : null);
        response.setEnviadoPorId(domain.getEnviadoPorId().toString());
        return response;
    }
}
