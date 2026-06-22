package com.lunorion.labs.core.caja.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class MovimientoCaja extends BaseEntity {

    private String tenantId;
    private String cierreCajaId;
    private String tipo;
    private String metodoPago;
    private BigDecimal monto;
    private String referencia;
    private String concepto;
    private String usuarioId;

    public MovimientoCaja() {}

    public MovimientoCaja(UUID id, String tenantId, String cierreCajaId, String tipo,
                          String metodoPago, BigDecimal monto, String concepto, String usuarioId) {
        super(id);
        this.tenantId = tenantId;
        this.cierreCajaId = cierreCajaId;
        this.tipo = tipo;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.concepto = concepto;
        this.usuarioId = usuarioId;
    }

    public static MovimientoCaja create(String tenantId, String cierreCajaId, String tipo,
                                        String metodoPago, BigDecimal monto, String concepto, String usuarioId) {
        return new MovimientoCaja(UUID.randomUUID(), tenantId, cierreCajaId, tipo,
                metodoPago, monto, concepto, usuarioId);
    }

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getCierreCajaId() { return cierreCajaId; }
    public void setCierreCajaId(String cierreCajaId) { this.cierreCajaId = cierreCajaId; }
    public String getTipo() { return tipo; }
    public String getMetodoPago() { return metodoPago; }
    public BigDecimal getMonto() { return monto; }
    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getConcepto() { return concepto; }
    public String getUsuarioId() { return usuarioId; }
}
