package com.lunorion.labs.core.orden_trabajo.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class OtInsumo extends BaseEntity {
    private String ordenTrabajoId;
    private String productoId;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    public OtInsumo() {}

    public OtInsumo(UUID id, String ordenTrabajoId, String productoId, BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
        super(id);
        this.ordenTrabajoId = ordenTrabajoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public static OtInsumo create(String ordenTrabajoId, String productoId, BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
        return new OtInsumo(UUID.randomUUID(), ordenTrabajoId, productoId, cantidad, precioUnitario, subtotal);
    }

    public String getOrdenTrabajoId() { return ordenTrabajoId; }
    public String getProductoId() { return productoId; }
    public BigDecimal getCantidad() { return cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }
}
