package com.lunorion.labs.core.comprobante_electronico.domain.ports.out;

import com.lunorion.labs.core.comprobante_electronico.domain.entity.ResumenDiario;

import java.util.List;
import java.util.Optional;

public interface IResumenDiarioRepositoryPort {
    ResumenDiario save(ResumenDiario resumen);
    Optional<ResumenDiario> findById(String id);
    List<ResumenDiario> findByTenantId(String tenantId);
}
