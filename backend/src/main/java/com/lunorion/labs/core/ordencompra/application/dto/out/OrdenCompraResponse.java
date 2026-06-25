package com.lunorion.labs.core.ordencompra.application.dto.out;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdenCompraResponse {
    private String id;
    private String tenantId;
    private String proveedorId;
    private String numeroOrden;
    private LocalDate fechaEmision;
    private String estado;
    private BigDecimal total;
    private String observacion;
    private String usuarioId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getProveedorId() { return proveedorId; }
    public void setProveedorId(String proveedorId) { this.proveedorId = proveedorId; }
    public String getNumeroOrden() { return numeroOrden; }
    public void setNumeroOrden(String numeroOrden) { this.numeroOrden = numeroOrden; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
}
