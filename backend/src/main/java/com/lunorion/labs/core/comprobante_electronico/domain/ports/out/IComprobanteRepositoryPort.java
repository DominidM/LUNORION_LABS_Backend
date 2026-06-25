package com.lunorion.labs.core.comprobante_electronico.domain.ports.out;

import com.lunorion.labs.core.comprobante_electronico.domain.entity.ComprobanteElectronico;
import com.lunorion.labs.core.comprobante_electronico.domain.entity.ResumenDiario;

import java.util.List;
import java.util.Optional;

public interface IComprobanteRepositoryPort {
    Optional<ComprobanteElectronico> findById(String id);
    List<ComprobanteElectronico> findByTenantId(String tenantId);
    List<ComprobanteElectronico> findByVentaId(String ventaId);
    List<ComprobanteElectronico> findByTenantIdAndFechaEmisionBetween(String tenantId, String fechaInicio, String fechaFin);
    ComprobanteElectronico save(ComprobanteElectronico comprobante);
    ResumenDiario saveResumenDiario(ResumenDiario resumenDiario);
    Optional<ResumenDiario> findResumenDiarioById(String id);
    List<ResumenDiario> findAllResumenDiario();
}
