package com.lunorion.labs.core.planilla.application.dto.out;

import java.math.BigDecimal;

public class BoletaPagoResponse {
    private String id;
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

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getTecnicoId() { return tecnicoId; }
    public void setTecnicoId(String tecnicoId) { this.tecnicoId = tecnicoId; }
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
}
