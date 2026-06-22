package com.lunorion.labs.core.planilla.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class BoletaPago {
    private UUID id;
    private String tenantId;
    private String tecnicoId;
    private String periodo;
    private BigDecimal sueldoBasico;
    private BigDecimal horasExtras;
    private BigDecimal comisiones;
    private BigDecimal asignacionFamiliar;
    private BigDecimal totalIngresos;
    private BigDecimal descuentoOnp;
    private BigDecimal descuentoAfp;
    private BigDecimal descuentoOtros;
    private BigDecimal totalDescuentos;
    private BigDecimal netoPagar;
    private BigDecimal essalud;
    private String pdfGenerado;
    private String pdfFirmado;

    public BoletaPago() {}

    public BoletaPago(UUID id, String tenantId, String tecnicoId, String periodo,
                      BigDecimal sueldoBasico, BigDecimal horasExtras, BigDecimal comisiones,
                      BigDecimal asignacionFamiliar) {
        this.id = id;
        this.tenantId = tenantId;
        this.tecnicoId = tecnicoId;
        this.periodo = periodo;
        this.sueldoBasico = sueldoBasico;
        this.horasExtras = horasExtras;
        this.comisiones = comisiones;
        this.asignacionFamiliar = asignacionFamiliar;
        calcular();
    }

    public static BoletaPago generar(String tenantId, String tecnicoId, String periodo,
                                      BigDecimal sueldoBasico, BigDecimal horasExtras,
                                      BigDecimal comisiones, BigDecimal asignacionFamiliar) {
        return new BoletaPago(UUID.randomUUID(), tenantId, tecnicoId, periodo,
                sueldoBasico, horasExtras, comisiones, asignacionFamiliar);
    }

    private void calcular() {
        this.totalIngresos = sueldoBasico
                .add(horasExtras != null ? horasExtras : BigDecimal.ZERO)
                .add(comisiones != null ? comisiones : BigDecimal.ZERO)
                .add(asignacionFamiliar != null ? asignacionFamiliar : BigDecimal.ZERO);

        this.essalud = totalIngresos.multiply(BigDecimal.valueOf(0.09))
                .setScale(2, java.math.RoundingMode.HALF_UP);

        this.descuentoOnp = BigDecimal.ZERO;
        this.descuentoAfp = BigDecimal.ZERO;
        this.descuentoOtros = BigDecimal.ZERO;
        this.totalDescuentos = descuentoOnp.add(descuentoAfp).add(descuentoOtros);
        this.netoPagar = totalIngresos.subtract(totalDescuentos);
    }

    public UUID getId() { return id; }
    public String getTenantId() { return tenantId; }
    public String getTecnicoId() { return tecnicoId; }
    public String getPeriodo() { return periodo; }
    public BigDecimal getSueldoBasico() { return sueldoBasico; }
    public BigDecimal getHorasExtras() { return horasExtras; }
    public BigDecimal getComisiones() { return comisiones; }
    public BigDecimal getAsignacionFamiliar() { return asignacionFamiliar; }
    public BigDecimal getTotalIngresos() { return totalIngresos; }
    public BigDecimal getDescuentoOnp() { return descuentoOnp; }
    public BigDecimal getDescuentoAfp() { return descuentoAfp; }
    public BigDecimal getDescuentoOtros() { return descuentoOtros; }
    public BigDecimal getTotalDescuentos() { return totalDescuentos; }
    public BigDecimal getNetoPagar() { return netoPagar; }
    public BigDecimal getEssalud() { return essalud; }
    public String getPdfGenerado() { return pdfGenerado; }
    public String getPdfFirmado() { return pdfFirmado; }

    public void setPdfGenerado(String pdfGenerado) { this.pdfGenerado = pdfGenerado; }
    public void setPdfFirmado(String pdfFirmado) { this.pdfFirmado = pdfFirmado; }
}
