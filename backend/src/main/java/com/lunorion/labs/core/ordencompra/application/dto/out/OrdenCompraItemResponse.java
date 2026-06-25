package com.lunorion.labs.core.ordencompra.application.dto.out;

import java.math.BigDecimal;

public class OrdenCompraItemResponse {
    private String id;
    private String ordenCompraId;
    private String productoId;
    private BigDecimal cantidad;
    private BigDecimal cantidadRecibida;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getOrdenCompraId() { return ordenCompraId; }
    public void setOrdenCompraId(String ordenCompraId) { this.ordenCompraId = ordenCompraId; }
    public String getProductoId() { return productoId; }
    public void setProductoId(String productoId) { this.productoId = productoId; }
    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }
    public BigDecimal getCantidadRecibida() { return cantidadRecibida; }
    public void setCantidadRecibida(BigDecimal cantidadRecibida) { this.cantidadRecibida = cantidadRecibida; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}
