package com.lunorion.labs.core.comprobante_electronico.application.dto.out;

import java.math.BigDecimal;

public class ReporteFacturacionResponse {
    private String periodo;
    private int totalComprobantes;
    private BigDecimal totalMontoFacturado;
    private int totalAceptados;
    private int totalRechazados;
    private int totalPendientes;

    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    public int getTotalComprobantes() { return totalComprobantes; }
    public void setTotalComprobantes(int totalComprobantes) { this.totalComprobantes = totalComprobantes; }
    public BigDecimal getTotalMontoFacturado() { return totalMontoFacturado; }
    public void setTotalMontoFacturado(BigDecimal totalMontoFacturado) { this.totalMontoFacturado = totalMontoFacturado; }
    public int getTotalAceptados() { return totalAceptados; }
    public void setTotalAceptados(int totalAceptados) { this.totalAceptados = totalAceptados; }
    public int getTotalRechazados() { return totalRechazados; }
    public void setTotalRechazados(int totalRechazados) { this.totalRechazados = totalRechazados; }
    public int getTotalPendientes() { return totalPendientes; }
    public void setTotalPendientes(int totalPendientes) { this.totalPendientes = totalPendientes; }
}
