package com.lunorion.labs.core.tecnico.application.dto.out;

import java.math.BigDecimal;

public class ConfiguracionComisionResponse {
    private String id;
    private String tenantId;
    private String tecnicoId;
    private String productoId;
    private String tipo;
    private BigDecimal porcentaje;
    private boolean activo;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getTecnicoId() { return tecnicoId; }
    public void setTecnicoId(String tecnicoId) { this.tecnicoId = tecnicoId; }
    public String getProductoId() { return productoId; }
    public void setProductoId(String productoId) { this.productoId = productoId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
