package com.lunorion.labs.core.producto.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;
import java.util.UUID;

public class CategoriaProducto extends BaseEntity {
    private String tenantId;
    private String nombre;
    private UUID categoriaPadreId;

    public CategoriaProducto() {}

    public CategoriaProducto(UUID id, String tenantId, String nombre) {
        super(id);
        this.tenantId = tenantId;
        this.nombre = nombre;
    }

    public static CategoriaProducto create(String tenantId, String nombre, UUID categoriaPadreId) {
        CategoriaProducto c = new CategoriaProducto(UUID.randomUUID(), tenantId, nombre);
        c.categoriaPadreId = categoriaPadreId;
        return c;
    }

    public String getTenantId() { return tenantId; }
    public String getNombre() { return nombre; }
    public UUID getCategoriaPadreId() { return categoriaPadreId; }
}
