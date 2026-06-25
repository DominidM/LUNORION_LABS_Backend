package com.lunorion.labs.core.venta.domain.ports.out;

import com.lunorion.labs.core.venta.domain.entity.Venta;
import com.lunorion.labs.core.venta.domain.entity.VentaItem;

import java.util.List;
import java.util.Optional;

public interface IVentaRepositoryPort {
    Venta save(Venta venta);
    VentaItem saveItem(VentaItem item);
    List<VentaItem> saveAllItems(List<VentaItem> items);
    Optional<Venta> findById(String id);
    List<Venta> findByTenantId(String tenantId);
    List<Venta> findByClienteId(String clienteId);
    List<Venta> findAll();
    void deleteById(String id);
}
