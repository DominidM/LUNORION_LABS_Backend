package com.lunorion.labs.core.caja.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "cierre_caja")
public class CierreCajaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    private LocalDate fecha;

    @Column(name = "hora_apertura")
    private LocalTime horaApertura;

    @Column(name = "hora_cierre")
    private LocalTime horaCierre;

    @Column(name = "saldo_inicial", precision = 12, scale = 2)
    private BigDecimal saldoInicial;

    @Column(name = "total_ingresos", precision = 12, scale = 2)
    private BigDecimal totalIngresos;

    @Column(name = "total_egresos", precision = 12, scale = 2)
    private BigDecimal totalEgresos;

    @Column(name = "saldo_esperado", precision = 12, scale = 2)
    private BigDecimal saldoEsperado;

    @Column(name = "saldo_real", precision = 12, scale = 2)
    private BigDecimal saldoReal;

    @Column(precision = 12, scale = 2)
    private BigDecimal descuadre;

    @Column(columnDefinition = "TEXT")
    private String observacion;

    @Column(name = "usuario_apertura_id")
    private String usuarioAperturaId;

    @Column(name = "usuario_cierre_id")
    private String usuarioCierreId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public CierreCajaEntity() {}

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHoraApertura() { return horaApertura; }
    public void setHoraApertura(LocalTime horaApertura) { this.horaApertura = horaApertura; }
    public LocalTime getHoraCierre() { return horaCierre; }
    public void setHoraCierre(LocalTime horaCierre) { this.horaCierre = horaCierre; }
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
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
