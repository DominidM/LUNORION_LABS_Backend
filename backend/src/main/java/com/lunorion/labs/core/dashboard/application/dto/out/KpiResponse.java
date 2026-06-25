package com.lunorion.labs.core.dashboard.application.dto.out;

import java.math.BigDecimal;

public class KpiResponse {

    private BigDecimal facturacionHoy;
    private long ordenesAbiertas;
    private long stockCritico;
    private long citasHoy;
    private BigDecimal ingresosMes;
    private BigDecimal egresosMes;

    public KpiResponse() {
    }

    public KpiResponse(BigDecimal facturacionHoy, long ordenesAbiertas, long stockCritico,
                       long citasHoy, BigDecimal ingresosMes, BigDecimal egresosMes) {
        this.facturacionHoy = facturacionHoy;
        this.ordenesAbiertas = ordenesAbiertas;
        this.stockCritico = stockCritico;
        this.citasHoy = citasHoy;
        this.ingresosMes = ingresosMes;
        this.egresosMes = egresosMes;
    }

    public BigDecimal getFacturacionHoy() {
        return facturacionHoy;
    }

    public void setFacturacionHoy(BigDecimal facturacionHoy) {
        this.facturacionHoy = facturacionHoy;
    }

    public long getOrdenesAbiertas() {
        return ordenesAbiertas;
    }

    public void setOrdenesAbiertas(long ordenesAbiertas) {
        this.ordenesAbiertas = ordenesAbiertas;
    }

    public long getStockCritico() {
        return stockCritico;
    }

    public void setStockCritico(long stockCritico) {
        this.stockCritico = stockCritico;
    }

    public long getCitasHoy() {
        return citasHoy;
    }

    public void setCitasHoy(long citasHoy) {
        this.citasHoy = citasHoy;
    }

    public BigDecimal getIngresosMes() {
        return ingresosMes;
    }

    public void setIngresosMes(BigDecimal ingresosMes) {
        this.ingresosMes = ingresosMes;
    }

    public BigDecimal getEgresosMes() {
        return egresosMes;
    }

    public void setEgresosMes(BigDecimal egresosMes) {
        this.egresosMes = egresosMes;
    }
}
