package com.lunorion.labs.core.comprobante_electronico.domain.ports.in;

import com.lunorion.labs.core.comprobante_electronico.application.dto.out.CdrResponse;
import com.lunorion.labs.core.comprobante_electronico.application.dto.out.ComprobanteResponse;
import com.lunorion.labs.core.comprobante_electronico.application.dto.out.PleResponse;
import com.lunorion.labs.core.comprobante_electronico.application.dto.out.ReporteFacturacionResponse;
import com.lunorion.labs.core.comprobante_electronico.application.dto.out.ResumenDiarioResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IComprobanteQueryPort {
    Optional<ComprobanteResponse> findById(UUID id);
    List<ComprobanteResponse> findByTenantId(String tenantId);
    List<ComprobanteResponse> findByVentaId(UUID ventaId);
    CdrResponse descargarCdr(UUID id);
    String descargarXml(UUID id);
    ReporteFacturacionResponse reporteFacturacion(String tenantId, LocalDate fechaInicio, LocalDate fechaFin);
    Optional<ResumenDiarioResponse> estadoResumenDiario(UUID id);
    PleResponse generarPle(String tenantId, String periodo);
    PleResponse descargarPle(UUID id);
}
