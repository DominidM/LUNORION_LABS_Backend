package com.lunorion.labs.core.comprobante_electronico.application.dto.in;

public class CreateResumenDiarioRequest {
    private String tenantId;
    private String fechaResumen;
    private String codigoResumen;
    private int totalBoletas;
    private int totalAnulaciones;

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getFechaResumen() { return fechaResumen; }
    public void setFechaResumen(String fechaResumen) { this.fechaResumen = fechaResumen; }
    public String getCodigoResumen() { return codigoResumen; }
    public void setCodigoResumen(String codigoResumen) { this.codigoResumen = codigoResumen; }
    public int getTotalBoletas() { return totalBoletas; }
    public void setTotalBoletas(int totalBoletas) { this.totalBoletas = totalBoletas; }
    public int getTotalAnulaciones() { return totalAnulaciones; }
    public void setTotalAnulaciones(int totalAnulaciones) { this.totalAnulaciones = totalAnulaciones; }
}
