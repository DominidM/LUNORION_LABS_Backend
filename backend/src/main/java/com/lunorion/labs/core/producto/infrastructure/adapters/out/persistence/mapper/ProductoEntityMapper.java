package com.lunorion.labs.core.producto.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.producto.domain.entity.CategoriaProducto;
import com.lunorion.labs.core.producto.domain.entity.Producto;
import com.lunorion.labs.core.producto.infrastructure.adapters.out.persistence.entity.CategoriaProductoEntity;
import com.lunorion.labs.core.producto.infrastructure.adapters.out.persistence.entity.ProductoEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductoEntityMapper {

    public ProductoEntity toEntity(Producto domain) {
        if (domain == null) return null;
        ProductoEntity entity = new ProductoEntity();
        entity.setId(domain.getId());
        entity.setTenantId(UUID.fromString(domain.getTenantId()));
        entity.setCategoriaId(domain.getCategoriaId());
        entity.setCodigo(domain.getCodigo());
        entity.setCodigoBarra(domain.getCodigoBarra());
        entity.setNombre(domain.getNombre());
        entity.setDescripcion(domain.getDescripcion());
        entity.setMarca(domain.getMarca());
        entity.setModelo(domain.getModelo());
        entity.setUnidadMedida(domain.getUnidadMedida());
        entity.setPrecioCompra(domain.getPrecioCompra());
        entity.setPrecioVenta(domain.getPrecioVenta());
        entity.setStockActual(domain.getStockActual());
        entity.setStockMinimo(domain.getStockMinimo());
        entity.setUbicacion(domain.getUbicacion());
        entity.setTipo(domain.getTipo());
        entity.setActivo(domain.isActivo());
        return entity;
    }

    public Producto toDomain(ProductoEntity entity) {
        if (entity == null) return null;
        Producto domain = new Producto(entity.getId(), entity.getTenantId().toString(),
                entity.getCategoriaId(), entity.getCodigo(), entity.getNombre());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    public CategoriaProductoEntity toCategoriaEntity(CategoriaProducto domain) {
        if (domain == null) return null;
        CategoriaProductoEntity entity = new CategoriaProductoEntity();
        entity.setId(domain.getId());
        entity.setTenantId(UUID.fromString(domain.getTenantId()));
        entity.setNombre(domain.getNombre());
        entity.setCategoriaPadreId(domain.getCategoriaPadreId());
        return entity;
    }

    public CategoriaProducto toCategoriaDomain(CategoriaProductoEntity entity) {
        if (entity == null) return null;
        CategoriaProducto domain = new CategoriaProducto(entity.getId(), entity.getTenantId().toString(), entity.getNombre());
        return domain;
    }
}
