package com.lunorion.labs.core.comprobante_electronico.application.dto.out;

public class ResumenDiarioResponse {
    private String id;
    private String tenantId;
    private String fechaResumen;
    private String codigoResumen;
    private String estado;
    private int totalBoletas;
    private int totalAnulaciones;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getFechaResumen() { return fechaResumen; }
    public void setFechaResumen(String fechaResumen) { this.fechaResumen = fechaResumen; }
    public String getCodigoResumen() { return codigoResumen; }
    public void setCodigoResumen(String codigoResumen) { this.codigoResumen = codigoResumen; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public int getTotalBoletas() { return totalBoletas; }
    public void setTotalBoletas(int totalBoletas) { this.totalBoletas = totalBoletas; }
    public int getTotalAnulaciones() { return totalAnulaciones; }
    public void setTotalAnulaciones(int totalAnulaciones) { this.totalAnulaciones = totalAnulaciones; }
}
