package com.lunorion.labs.core.producto.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.producto.infrastructure.adapters.out.persistence.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, UUID> {
    Optional<ProductoEntity> findByCodigo(String codigo);
    List<ProductoEntity> findByTenantId(UUID tenantId);
    List<ProductoEntity> findByCategoriaId(UUID categoriaId);
    @Query("SELECT p FROM ProductoEntity p WHERE p.tenantId = :tenantId AND p.stockActual IS NOT NULL AND p.stockMinimo IS NOT NULL AND p.stockActual <= p.stockMinimo")
    List<ProductoEntity> findStockCritico(@Param("tenantId") UUID tenantId);
}
