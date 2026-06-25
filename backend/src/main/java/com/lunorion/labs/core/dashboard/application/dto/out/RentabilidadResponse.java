package com.lunorion.labs.core.dashboard.application.dto.out;

import java.math.BigDecimal;

public class RentabilidadResponse {

    private String servicio;
    private BigDecimal ingresos;
    private BigDecimal costos;
    private BigDecimal margen;
    private Double porcentaje;

    public RentabilidadResponse() {
    }

    public RentabilidadResponse(String servicio, BigDecimal ingresos, BigDecimal costos,
                                BigDecimal margen, Double porcentaje) {
        this.servicio = servicio;
        this.ingresos = ingresos;
        this.costos = costos;
        this.margen = margen;
        this.porcentaje = porcentaje;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public BigDecimal getIngresos() {
        return ingresos;
    }

    public void setIngresos(BigDecimal ingresos) {
        this.ingresos = ingresos;
    }

    public BigDecimal getCostos() {
        return costos;
    }

    public void setCostos(BigDecimal costos) {
        this.costos = costos;
    }

    public BigDecimal getMargen() {
        return margen;
    }

    public void setMargen(BigDecimal margen) {
        this.margen = margen;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
