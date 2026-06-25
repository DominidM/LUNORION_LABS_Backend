package com.lunorion.labs.core.caja.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class CierreCaja extends BaseEntity {

    private String tenantId;
    private LocalDate fecha;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private BigDecimal saldoInicial;
    private BigDecimal totalIngresos;
    private BigDecimal totalEgresos;
    private BigDecimal saldoEsperado;
    private BigDecimal saldoReal;
    private BigDecimal descuadre;
    private String observacion;
    private String usuarioAperturaId;
    private String usuarioCierreId;

    public CierreCaja() {}

    public CierreCaja(UUID id, String tenantId, LocalDate fecha, LocalTime horaApertura,
                      BigDecimal saldoInicial, String usuarioAperturaId) {
        super(id);
        this.tenantId = tenantId;
        this.fecha = fecha;
        this.horaApertura = horaApertura;
        this.saldoInicial = saldoInicial;
        this.usuarioAperturaId = usuarioAperturaId;
        this.totalIngresos = BigDecimal.ZERO;
        this.totalEgresos = BigDecimal.ZERO;
    }

    public static CierreCaja create(String tenantId, LocalDate fecha, LocalTime horaApertura,
                                    BigDecimal saldoInicial, String usuarioAperturaId) {
        return new CierreCaja(UUID.randomUUID(), tenantId, fecha, horaApertura, saldoInicial, usuarioAperturaId);
    }

    public void cerrar(LocalTime horaCierre, BigDecimal saldoReal, String observacion, String usuarioCierreId) {
        this.horaCierre = horaCierre;
        this.saldoReal = saldoReal;
        this.observacion = observacion;
        this.usuarioCierreId = usuarioCierreId;
        this.saldoEsperado = saldoInicial.add(totalIngresos).subtract(totalEgresos);
        this.descuadre = saldoReal.subtract(saldoEsperado);
        markUpdated();
    }

    public String getTenantId() { return tenantId; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraApertura() { return horaApertura; }
    public LocalTime getHoraCierre() { return horaCierre; }
    public BigDecimal getSaldoInicial() { return saldoInicial; }
    public BigDecimal getTotalIngresos() { return totalIngresos; }
    public void setTotalIngresos(BigDecimal totalIngresos) { this.totalIngresos = totalIngresos; }
    public BigDecimal getTotalEgresos() { return totalEgresos; }
    public void setTotalEgresos(BigDecimal totalEgresos) { this.totalEgresos = totalEgresos; }
    public BigDecimal getSaldoEsperado() { return saldoEsperado; }
    public BigDecimal getSaldoReal() { return saldoReal; }
    public BigDecimal getDescuadre() { return descuadre; }
    public String getObservacion() { return observacion; }
    public String getUsuarioAperturaId() { return usuarioAperturaId; }
    public String getUsuarioCierreId() { return usuarioCierreId; }
}
