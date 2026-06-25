package com.lunorion.labs.core.cotizacion.application.dto.out;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CotizacionResponse {
    private String id;
    private String tenantId;
    private String clienteId;
    private String vehiculoId;
    private LocalDate fechaEmision;
    private LocalDate fechaValidez;
    private String estado;
    private BigDecimal subtotal;
    private BigDecimal igv;
    private BigDecimal total;
    private String notas;
    private List<CotizacionItemResponse> items;
    private boolean activo;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(String vehiculoId) { this.vehiculoId = vehiculoId; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }
    public LocalDate getFechaValidez() { return fechaValidez; }
    public void setFechaValidez(LocalDate fechaValidez) { this.fechaValidez = fechaValidez; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getIgv() { return igv; }
    public void setIgv(BigDecimal igv) { this.igv = igv; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
    public List<CotizacionItemResponse> getItems() { return items; }
    public void setItems(List<CotizacionItemResponse> items) { this.items = items; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
