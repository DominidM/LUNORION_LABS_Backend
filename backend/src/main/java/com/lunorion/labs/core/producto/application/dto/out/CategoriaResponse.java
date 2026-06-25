package com.lunorion.labs.core.producto.application.dto.out;

import java.util.UUID;

public class CategoriaResponse {
    private String id;
    private String tenantId;
    private String nombre;
    private UUID categoriaPadreId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public UUID getCategoriaPadreId() { return categoriaPadreId; }
    public void setCategoriaPadreId(UUID categoriaPadreId) { this.categoriaPadreId = categoriaPadreId; }
}
