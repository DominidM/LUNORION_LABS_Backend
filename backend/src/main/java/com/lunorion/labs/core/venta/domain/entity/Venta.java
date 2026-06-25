package com.lunorion.labs.core.venta.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class Venta extends BaseEntity {
    private String tenantId;
    private String clienteId;
    private String tipo;
    private BigDecimal subtotal;
    private BigDecimal igv;
    private BigDecimal total;
    private BigDecimal descuento;
    private String metodoPago;
    private String estadoPago;
    private String usuarioId;

    public Venta() {}

    public Venta(UUID id, String tenantId, String clienteId, String tipo, BigDecimal subtotal, BigDecimal igv, BigDecimal total, String usuarioId) {
        super(id);
        this.tenantId = tenantId;
        this.clienteId = clienteId;
        this.tipo = tipo;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.usuarioId = usuarioId;
        this.descuento = BigDecimal.ZERO;
    }

    public static Venta create(String tenantId, String clienteId, String tipo, BigDecimal subtotal, BigDecimal igv, BigDecimal total, String usuarioId) {
        return new Venta(UUID.randomUUID(), tenantId, clienteId, tipo, subtotal, igv, total, usuarioId);
    }

    public String getTenantId() { return tenantId; }
    public String getClienteId() { return clienteId; }
    public String getTipo() { return tipo; }
    public BigDecimal getSubtotal() { return subtotal; }
    public BigDecimal getIgv() { return igv; }
    public BigDecimal getTotal() { return total; }
    public BigDecimal getDescuento() { return descuento; }
    public String getMetodoPago() { return metodoPago; }
    public String getEstadoPago() { return estadoPago; }
    public String getUsuarioId() { return usuarioId; }
}
