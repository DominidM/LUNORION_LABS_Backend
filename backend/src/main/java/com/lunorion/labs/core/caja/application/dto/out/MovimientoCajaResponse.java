package com.lunorion.labs.core.caja.application.dto.out;

import java.math.BigDecimal;

public class MovimientoCajaResponse {
    private String id;
    private String cierreCajaId;
    private String tipo;
    private String metodoPago;
    private BigDecimal monto;
    private String referencia;
    private String concepto;
    private String usuarioId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCierreCajaId() { return cierreCajaId; }
    public void setCierreCajaId(String cierreCajaId) { this.cierreCajaId = cierreCajaId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
}
