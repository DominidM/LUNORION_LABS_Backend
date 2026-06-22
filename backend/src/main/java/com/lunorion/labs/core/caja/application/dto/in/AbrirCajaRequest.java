package com.lunorion.labs.core.caja.application.dto.in;

import java.math.BigDecimal;

public class AbrirCajaRequest {
    private String tenantId;
    private String fecha;
    private String horaApertura;
    private BigDecimal saldoInicial;
    private String usuarioAperturaId;

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getHoraApertura() { return horaApertura; }
    public void setHoraApertura(String horaApertura) { this.horaApertura = horaApertura; }
    public BigDecimal getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(BigDecimal saldoInicial) { this.saldoInicial = saldoInicial; }
    public String getUsuarioAperturaId() { return usuarioAperturaId; }
    public void setUsuarioAperturaId(String usuarioAperturaId) { this.usuarioAperturaId = usuarioAperturaId; }
}
