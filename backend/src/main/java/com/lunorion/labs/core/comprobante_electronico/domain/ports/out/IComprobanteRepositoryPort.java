package com.lunorion.labs.core.comprobante_electronico.domain.ports.out;

import com.lunorion.labs.core.comprobante_electronico.domain.entity.ComprobanteElectronico;
import com.lunorion.labs.core.comprobante_electronico.domain.entity.ResumenDiario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IComprobanteRepositoryPort {
    Optional<ComprobanteElectronico> findById(UUID id);
    List<ComprobanteElectronico> findByTenantId(String tenantId);
    List<ComprobanteElectronico> findByVentaId(UUID ventaId);
    List<ComprobanteElectronico> findByTenantIdAndFechaEmisionBetween(String tenantId, LocalDate fechaInicio, LocalDate fechaFin);
    ComprobanteElectronico save(ComprobanteElectronico comprobante);
    ResumenDiario saveResumenDiario(ResumenDiario resumenDiario);
    Optional<ResumenDiario> findResumenDiarioById(UUID id);
    List<ResumenDiario> findAllResumenDiario();
}
