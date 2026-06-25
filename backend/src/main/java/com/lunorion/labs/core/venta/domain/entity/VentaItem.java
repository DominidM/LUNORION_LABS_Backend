package com.lunorion.labs.core.venta.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class VentaItem extends BaseEntity {
    private String ventaId;
    private String productoId;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal descuento;
    private BigDecimal subtotal;

    public VentaItem() {}

    public VentaItem(UUID id, String ventaId, String productoId, BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal descuento, BigDecimal subtotal) {
        super(id);
        this.ventaId = ventaId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.subtotal = subtotal;
    }

    public static VentaItem create(String ventaId, String productoId, BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal descuento, BigDecimal subtotal) {
        return new VentaItem(UUID.randomUUID(), ventaId, productoId, cantidad, precioUnitario, descuento, subtotal);
    }

    public String getVentaId() { return ventaId; }
    public String getProductoId() { return productoId; }
    public BigDecimal getCantidad() { return cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public BigDecimal getDescuento() { return descuento; }
    public BigDecimal getSubtotal() { return subtotal; }
}
