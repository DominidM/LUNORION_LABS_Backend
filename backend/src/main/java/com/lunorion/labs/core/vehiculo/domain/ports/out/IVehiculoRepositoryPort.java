package com.lunorion.labs.core.vehiculo.domain.ports.out;

import com.lunorion.labs.core.vehiculo.domain.entity.Vehiculo;
import java.util.List;
import java.util.Optional;

public interface IVehiculoRepositoryPort {
    Vehiculo save(Vehiculo vehiculo);
    Optional<Vehiculo> findById(String id);
    Optional<Vehiculo> findByPlaca(String placa);
    List<Vehiculo> findByTenantId(String tenantId);
    List<Vehiculo> findByClienteId(String clienteId);
    List<Vehiculo> findAll();
}
