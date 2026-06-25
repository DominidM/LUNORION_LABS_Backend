package com.lunorion.labs.core.cliente.application.dto.out;

import java.math.BigDecimal;

public class RentabilidadClienteResponse {
    private String clienteId;
    private String nombreCliente;
    private BigDecimal totalFacturado;
    private BigDecimal totalCostos;
    private BigDecimal margen;
    private int ordenesCompletadas;

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public BigDecimal getTotalFacturado() { return totalFacturado; }
    public void setTotalFacturado(BigDecimal totalFacturado) { this.totalFacturado = totalFacturado; }
    public BigDecimal getTotalCostos() { return totalCostos; }
    public void setTotalCostos(BigDecimal totalCostos) { this.totalCostos = totalCostos; }
    public BigDecimal getMargen() { return margen; }
    public void setMargen(BigDecimal margen) { this.margen = margen; }
    public int getOrdenesCompletadas() { return ordenesCompletadas; }
    public void setOrdenesCompletadas(int ordenesCompletadas) { this.ordenesCompletadas = ordenesCompletadas; }
}
