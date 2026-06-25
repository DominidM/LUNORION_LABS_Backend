package com.lunorion.labs.core.producto.domain.ports.out;

import com.lunorion.labs.core.producto.domain.entity.CategoriaProducto;
import com.lunorion.labs.core.producto.domain.entity.Producto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductoRepositoryPort {
    Producto save(Producto producto);
    Optional<Producto> findById(String id);
    Optional<Producto> findByCodigo(String codigo);
    List<Producto> findByTenantId(String tenantId);
    List<Producto> findByCategoriaId(UUID categoriaId);
    List<Producto> findStockCritico(String tenantId);
    void deleteById(String id);

    CategoriaProducto saveCategoria(CategoriaProducto categoria);
    Optional<CategoriaProducto> findCategoriaById(String id);
    List<CategoriaProducto> findAllCategorias();
    void deleteCategoriaById(String id);
}
