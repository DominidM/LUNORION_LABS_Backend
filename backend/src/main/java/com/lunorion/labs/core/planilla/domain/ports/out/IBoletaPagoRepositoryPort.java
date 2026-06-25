package com.lunorion.labs.core.planilla.domain.ports.out;

import com.lunorion.labs.core.planilla.domain.entity.BoletaPago;

import java.util.List;
import java.util.Optional;

public interface IBoletaPagoRepositoryPort {
    BoletaPago save(BoletaPago boleta);
    Optional<BoletaPago> findById(String id);
    Optional<BoletaPago> findByTecnicoIdAndPeriodo(String tecnicoId, String periodo);
    List<BoletaPago> findByTenantIdAndPeriodo(String tenantId, String periodo);
}
