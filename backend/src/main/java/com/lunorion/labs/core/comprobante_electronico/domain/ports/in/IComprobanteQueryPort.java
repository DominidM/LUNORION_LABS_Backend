package com.lunorion.labs.core.comprobante_electronico.domain.ports.in;

import com.lunorion.labs.core.comprobante_electronico.application.dto.out.ComprobanteResponse;

import java.util.List;
import java.util.Optional;

public interface IComprobanteQueryPort {
    Optional<ComprobanteResponse> findById(String id);
    List<ComprobanteResponse> findByTenantId(String tenantId);
    List<ComprobanteResponse> findByVentaId(String ventaId);
}
