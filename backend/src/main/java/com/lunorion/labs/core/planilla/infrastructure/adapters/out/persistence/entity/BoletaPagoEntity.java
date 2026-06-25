package com.lunorion.labs.core.planilla.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "boleta_pago")
public class BoletaPagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "tecnico_id", nullable = false)
    private UUID tecnicoId;

    @Column(length = 6)
    private String periodo;

    @Column(name = "sueldo_basico", precision = 10, scale = 2)
    private BigDecimal sueldoBasico;

    @Column(name = "horas_extras", precision = 10, scale = 2)
    private BigDecimal horasExtras;

    @Column(precision = 10, scale = 2)
    private BigDecimal comisiones;

    @Column(name = "asignacion_familiar", precision = 10, scale = 2)
    private BigDecimal asignacionFamiliar;

    @Column(name = "total_ingresos", precision = 10, scale = 2)
    private BigDecimal totalIngresos;

    @Column(name = "descuento_onp", precision = 10, scale = 2)
    private BigDecimal descuentoOnp;

    @Column(name = "descuento_afp", precision = 10, scale = 2)
    private BigDecimal descuentoAfp;

    @Column(name = "descuento_otros", precision = 10, scale = 2)
    private BigDecimal descuentoOtros;

    @Column(name = "total_descuentos", precision = 10, scale = 2)
    private BigDecimal totalDescuentos;

    @Column(name = "neto_pagar", precision = 10, scale = 2)
    private BigDecimal netoPagar;

    @Column(precision = 10, scale = 2)
    private BigDecimal essalud;

    @Column(name = "pdf_generado", columnDefinition = "TEXT")
    private String pdfGenerado;

    @Column(name = "pdf_firmado", columnDefinition = "TEXT")
    private String pdfFirmado;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public BoletaPagoEntity() {}

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getTecnicoId() { return tecnicoId; }
    public void setTecnicoId(UUID tecnicoId) { this.tecnicoId = tecnicoId; }
    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    public BigDecimal getSueldoBasico() { return sueldoBasico; }
    public void setSueldoBasico(BigDecimal sueldoBasico) { this.sueldoBasico = sueldoBasico; }
    public BigDecimal getHorasExtras() { return horasExtras; }
    public void setHorasExtras(BigDecimal horasExtras) { this.horasExtras = horasExtras; }
    public BigDecimal getComisiones() { return comisiones; }
    public void setComisiones(BigDecimal comisiones) { this.comisiones = comisiones; }
    public BigDecimal getAsignacionFamiliar() { return asignacionFamiliar; }
    public void setAsignacionFamiliar(BigDecimal asignacionFamiliar) { this.asignacionFamiliar = asignacionFamiliar; }
    public BigDecimal getTotalIngresos() { return totalIngresos; }
    public void setTotalIngresos(BigDecimal totalIngresos) { this.totalIngresos = totalIngresos; }
    public BigDecimal getDescuentoOnp() { return descuentoOnp; }
    public void setDescuentoOnp(BigDecimal descuentoOnp) { this.descuentoOnp = descuentoOnp; }
    public BigDecimal getDescuentoAfp() { return descuentoAfp; }
    public void setDescuentoAfp(BigDecimal descuentoAfp) { this.descuentoAfp = descuentoAfp; }
    public BigDecimal getDescuentoOtros() { return descuentoOtros; }
    public void setDescuentoOtros(BigDecimal descuentoOtros) { this.descuentoOtros = descuentoOtros; }
    public BigDecimal getTotalDescuentos() { return totalDescuentos; }
    public void setTotalDescuentos(BigDecimal totalDescuentos) { this.totalDescuentos = totalDescuentos; }
    public BigDecimal getNetoPagar() { return netoPagar; }
    public void setNetoPagar(BigDecimal netoPagar) { this.netoPagar = netoPagar; }
    public BigDecimal getEssalud() { return essalud; }
    public void setEssalud(BigDecimal essalud) { this.essalud = essalud; }
    public String getPdfGenerado() { return pdfGenerado; }
    public void setPdfGenerado(String pdfGenerado) { this.pdfGenerado = pdfGenerado; }
    public String getPdfFirmado() { return pdfFirmado; }
    public void setPdfFirmado(String pdfFirmado) { this.pdfFirmado = pdfFirmado; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
