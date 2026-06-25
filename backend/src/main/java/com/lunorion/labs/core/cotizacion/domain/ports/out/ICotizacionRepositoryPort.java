package com.lunorion.labs.core.cotizacion.domain.ports.out;

import com.lunorion.labs.core.cotizacion.domain.entity.Cotizacion;

import java.util.List;
import java.util.Optional;

public interface ICotizacionRepositoryPort {
    Cotizacion save(Cotizacion cotizacion);
    Optional<Cotizacion> findById(String id);
    List<Cotizacion> findByTenantId(String tenantId);
    List<Cotizacion> findByClienteId(String clienteId);
    List<Cotizacion> findByEstado(String estado);
    List<Cotizacion> findAll();
}
