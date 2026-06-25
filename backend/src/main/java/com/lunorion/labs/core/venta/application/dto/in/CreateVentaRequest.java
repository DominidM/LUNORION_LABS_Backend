package com.lunorion.labs.core.venta.application.dto.in;

import java.math.BigDecimal;
import java.util.List;

public class CreateVentaRequest {
    private String tenantId;
    private String clienteId;
    private String tipo;
    private BigDecimal subtotal;
    private BigDecimal igv;
    private BigDecimal total;
    private List<CreateVentaItemRequest> items;

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getIgv() { return igv; }
    public void setIgv(BigDecimal igv) { this.igv = igv; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public List<CreateVentaItemRequest> getItems() { return items; }
    public void setItems(List<CreateVentaItemRequest> items) { this.items = items; }

    public static class CreateVentaItemRequest {
        private String productoId;
        private BigDecimal cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal descuento;
        private BigDecimal subtotal;

        public String getProductoId() { return productoId; }
        public void setProductoId(String productoId) { this.productoId = productoId; }
        public BigDecimal getCantidad() { return cantidad; }
        public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }
        public BigDecimal getPrecioUnitario() { return precioUnitario; }
        public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
        public BigDecimal getDescuento() { return descuento; }
        public void setDescuento(BigDecimal descuento) { this.descuento = descuento; }
        public BigDecimal getSubtotal() { return subtotal; }
        public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    }
}
