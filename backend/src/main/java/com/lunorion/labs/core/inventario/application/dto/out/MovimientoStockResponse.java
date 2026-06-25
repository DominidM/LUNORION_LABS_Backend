package com.lunorion.labs.core.inventario.application.dto.out;

import java.math.BigDecimal;

public class MovimientoStockResponse {
    private String id;
    private String tenantId;
    private String productoId;
    private String tipo;
    private String subtipo;
    private BigDecimal cantidad;
    private BigDecimal costoUnitario;
    private BigDecimal stockAnterior;
    private BigDecimal stockPosterior;
    private String documentoOrigen;
    private String observacion;
    private String usuarioId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getProductoId() { return productoId; }
    public void setProductoId(String productoId) { this.productoId = productoId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getSubtipo() { return subtipo; }
    public void setSubtipo(String subtipo) { this.subtipo = subtipo; }
    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }
    public BigDecimal getCostoUnitario() { return costoUnitario; }
    public void setCostoUnitario(BigDecimal costoUnitario) { this.costoUnitario = costoUnitario; }
    public BigDecimal getStockAnterior() { return stockAnterior; }
    public void setStockAnterior(BigDecimal stockAnterior) { this.stockAnterior = stockAnterior; }
    public BigDecimal getStockPosterior() { return stockPosterior; }
    public void setStockPosterior(BigDecimal stockPosterior) { this.stockPosterior = stockPosterior; }
    public String getDocumentoOrigen() { return documentoOrigen; }
    public void setDocumentoOrigen(String documentoOrigen) { this.documentoOrigen = documentoOrigen; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
}
