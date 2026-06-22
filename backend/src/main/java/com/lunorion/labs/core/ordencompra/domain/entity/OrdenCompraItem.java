package com.lunorion.labs.core.ordencompra.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.UUID;

public class OrdenCompraItem extends BaseEntity {

    private String ordenCompraId;
    private String productoId;
    private BigDecimal cantidad;
    private BigDecimal cantidadRecibida;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    public OrdenCompraItem() {}

    public OrdenCompraItem(UUID id, String ordenCompraId, String productoId, BigDecimal cantidad,
                           BigDecimal cantidadRecibida, BigDecimal precioUnitario, BigDecimal subtotal) {
        super(id);
        this.ordenCompraId = ordenCompraId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.cantidadRecibida = cantidadRecibida;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public static OrdenCompraItem create(String ordenCompraId, String productoId, BigDecimal cantidad,
                                         BigDecimal cantidadRecibida, BigDecimal precioUnitario, BigDecimal subtotal) {
        return new OrdenCompraItem(UUID.randomUUID(), ordenCompraId, productoId, cantidad,
                cantidadRecibida, precioUnitario, subtotal);
    }

    public String getOrdenCompraId() { return ordenCompraId; }
    public String getProductoId() { return productoId; }
    public BigDecimal getCantidad() { return cantidad; }
    public BigDecimal getCantidadRecibida() { return cantidadRecibida; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setCantidadRecibida(BigDecimal cantidadRecibida) { this.cantidadRecibida = cantidadRecibida; markUpdated(); }
}
