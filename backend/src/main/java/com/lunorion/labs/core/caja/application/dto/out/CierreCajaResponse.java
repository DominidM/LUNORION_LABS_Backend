package com.lunorion.labs.core.caja.application.dto.out;

import java.math.BigDecimal;

public class CierreCajaResponse {
    private String id;
    private String tenantId;
    private String fecha;
    private String horaApertura;
    private String horaCierre;
    private BigDecimal saldoInicial;
    private BigDecimal totalIngresos;
    private BigDecimal totalEgresos;
    private BigDecimal saldoEsperado;
    private BigDecimal saldoReal;
    private BigDecimal descuadre;
    private String observacion;
    private String usuarioAperturaId;
    private String usuarioCierreId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getHoraApertura() { return horaApertura; }
    public void setHoraApertura(String horaApertura) { this.horaApertura = horaApertura; }
    public String getHoraCierre() { return horaCierre; }
    public void setHoraCierre(String horaCierre) { this.horaCierre = horaCierre; }
    public BigDecimal getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(BigDecimal saldoInicial) { this.saldoInicial = saldoInicial; }
    public BigDecimal getTotalIngresos() { return totalIngresos; }
    public void setTotalIngresos(BigDecimal totalIngresos) { this.totalIngresos = totalIngresos; }
    public BigDecimal getTotalEgresos() { return totalEgresos; }
    public void setTotalEgresos(BigDecimal totalEgresos) { this.totalEgresos = totalEgresos; }
    public BigDecimal getSaldoEsperado() { return saldoEsperado; }
    public void setSaldoEsperado(BigDecimal saldoEsperado) { this.saldoEsperado = saldoEsperado; }
    public BigDecimal getSaldoReal() { return saldoReal; }
    public void setSaldoReal(BigDecimal saldoReal) { this.saldoReal = saldoReal; }
    public BigDecimal getDescuadre() { return descuadre; }
    public void setDescuadre(BigDecimal descuadre) { this.descuadre = descuadre; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public String getUsuarioAperturaId() { return usuarioAperturaId; }
    public void setUsuarioAperturaId(String usuarioAperturaId) { this.usuarioAperturaId = usuarioAperturaId; }
    public String getUsuarioCierreId() { return usuarioCierreId; }
    public void setUsuarioCierreId(String usuarioCierreId) { this.usuarioCierreId = usuarioCierreId; }
}
