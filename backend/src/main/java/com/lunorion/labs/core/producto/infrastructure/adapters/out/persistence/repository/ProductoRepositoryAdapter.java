package com.lunorion.labs.core.producto.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.producto.domain.entity.CategoriaProducto;
import com.lunorion.labs.core.producto.domain.entity.Producto;
import com.lunorion.labs.core.producto.domain.ports.out.IProductoRepositoryPort;
import com.lunorion.labs.core.producto.infrastructure.adapters.out.persistence.mapper.ProductoEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductoRepositoryAdapter implements IProductoRepositoryPort {

    private final ProductoJpaRepository jpaRepository;
    private final CategoriaProductoJpaRepository categoriaJpaRepository;
    private final ProductoEntityMapper mapper;

    public ProductoRepositoryAdapter(ProductoJpaRepository jpaRepository,
                                      CategoriaProductoJpaRepository categoriaJpaRepository,
                                      ProductoEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.categoriaJpaRepository = categoriaJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Producto save(Producto producto) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(producto)));
    }

    @Override
    public Optional<Producto> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public Optional<Producto> findByCodigo(String codigo) {
        return jpaRepository.findByCodigo(codigo).map(mapper::toDomain);
    }

    @Override
    public List<Producto> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Producto> findByCategoriaId(UUID categoriaId) {
        return jpaRepository.findByCategoriaId(categoriaId).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<Producto> findStockCritico(String tenantId) {
        return jpaRepository.findStockCritico(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public CategoriaProducto saveCategoria(CategoriaProducto categoria) {
        return mapper.toCategoriaDomain(categoriaJpaRepository.save(mapper.toCategoriaEntity(categoria)));
    }

    @Override
    public Optional<CategoriaProducto> findCategoriaById(String id) {
        return categoriaJpaRepository.findById(UUID.fromString(id)).map(mapper::toCategoriaDomain);
    }

    @Override
    public List<CategoriaProducto> findAllCategorias() {
        return categoriaJpaRepository.findAll().stream()
                .map(mapper::toCategoriaDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteCategoriaById(String id) {
        categoriaJpaRepository.deleteById(UUID.fromString(id));
    }
}
