package com.lunorion.labs.core.caja.application.dto.out;

import java.math.BigDecimal;

public class EstadoCajaResponse {
    private String id;
    private String tenantId;
    private BigDecimal saldoInicial;
    private BigDecimal totalIngresos;
    private BigDecimal totalEgresos;
    private BigDecimal saldoActual;
    private String fechaApertura;
    private String estado;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public BigDecimal getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(BigDecimal saldoInicial) { this.saldoInicial = saldoInicial; }
    public BigDecimal getTotalIngresos() { return totalIngresos; }
    public void setTotalIngresos(BigDecimal totalIngresos) { this.totalIngresos = totalIngresos; }
    public BigDecimal getTotalEgresos() { return totalEgresos; }
    public void setTotalEgresos(BigDecimal totalEgresos) { this.totalEgresos = totalEgresos; }
    public BigDecimal getSaldoActual() { return saldoActual; }
    public void setSaldoActual(BigDecimal saldoActual) { this.saldoActual = saldoActual; }
    public String getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(String fechaApertura) { this.fechaApertura = fechaApertura; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
