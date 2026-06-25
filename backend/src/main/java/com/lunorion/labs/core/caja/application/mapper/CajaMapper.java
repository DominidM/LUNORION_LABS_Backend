package com.lunorion.labs.core.caja.application.mapper;

import com.lunorion.labs.core.caja.application.dto.in.AbrirCajaRequest;
import com.lunorion.labs.core.caja.application.dto.in.RegistrarMovimientoRequest;
import com.lunorion.labs.core.caja.application.dto.out.CierreCajaResponse;
import com.lunorion.labs.core.caja.application.dto.out.MovimientoCajaResponse;
import com.lunorion.labs.core.caja.domain.entity.CierreCaja;
import com.lunorion.labs.core.caja.domain.entity.MovimientoCaja;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class CajaMapper {

    public CierreCaja toDomain(AbrirCajaRequest request) {
        return CierreCaja.create(
            request.getTenantId(),
            LocalDate.parse(request.getFecha()),
            LocalTime.parse(request.getHoraApertura()),
            request.getSaldoInicial(),
            request.getUsuarioAperturaId()
        );
    }

    public CierreCajaResponse toResponse(CierreCaja domain) {
        CierreCajaResponse response = new CierreCajaResponse();
        response.setId(domain.getId().toString());
        response.setTenantId(domain.getTenantId());
        response.setFecha(domain.getFecha() != null ? domain.getFecha().toString() : null);
        response.setHoraApertura(domain.getHoraApertura() != null ? domain.getHoraApertura().toString() : null);
        response.setHoraCierre(domain.getHoraCierre() != null ? domain.getHoraCierre().toString() : null);
        response.setSaldoInicial(domain.getSaldoInicial());
        response.setTotalIngresos(domain.getTotalIngresos());
        response.setTotalEgresos(domain.getTotalEgresos());
        response.setSaldoEsperado(domain.getSaldoEsperado());
        response.setSaldoReal(domain.getSaldoReal());
        response.setDescuadre(domain.getDescuadre());
        response.setObservacion(domain.getObservacion());
        response.setUsuarioAperturaId(domain.getUsuarioAperturaId());
        response.setUsuarioCierreId(domain.getUsuarioCierreId());
        return response;
    }

    public MovimientoCaja toDomain(RegistrarMovimientoRequest request) {
        MovimientoCaja domain = MovimientoCaja.create(
            null,
            request.getCierreCajaId(),
            request.getTipo(),
            request.getMetodoPago(),
            request.getMonto(),
            request.getConcepto(),
            request.getUsuarioId()
        );
        domain.setReferencia(request.getReferencia());
        return domain;
    }

    public MovimientoCajaResponse toResponse(MovimientoCaja domain) {
        MovimientoCajaResponse response = new MovimientoCajaResponse();
        response.setId(domain.getId().toString());
        response.setCierreCajaId(domain.getCierreCajaId());
        response.setTipo(domain.getTipo());
        response.setMetodoPago(domain.getMetodoPago());
        response.setMonto(domain.getMonto());
        response.setReferencia(domain.getReferencia());
        response.setConcepto(domain.getConcepto());
        response.setUsuarioId(domain.getUsuarioId());
        return response;
    }
}
