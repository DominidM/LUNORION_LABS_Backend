package com.lunorion.labs.core.ordencompra.domain.ports.out;

import com.lunorion.labs.core.ordencompra.domain.entity.OrdenCompraItem;

import java.util.List;
import java.util.Optional;

public interface IOrdenCompraItemRepositoryPort {
    OrdenCompraItem save(OrdenCompraItem item);
    Optional<OrdenCompraItem> findById(String id);
    List<OrdenCompraItem> findByOrdenCompraId(String ordenCompraId);
    void deleteById(String id);
}
