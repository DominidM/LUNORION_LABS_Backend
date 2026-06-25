package com.lunorion.labs.core.caja.application.dto.in;

import java.math.BigDecimal;

public class CerrarCajaRequest {
    private String horaCierre;
    private BigDecimal saldoReal;
    private String observacion;
    private String usuarioCierreId;

    public String getHoraCierre() { return horaCierre; }
    public void setHoraCierre(String horaCierre) { this.horaCierre = horaCierre; }
    public BigDecimal getSaldoReal() { return saldoReal; }
    public void setSaldoReal(BigDecimal saldoReal) { this.saldoReal = saldoReal; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public String getUsuarioCierreId() { return usuarioCierreId; }
    public void setUsuarioCierreId(String usuarioCierreId) { this.usuarioCierreId = usuarioCierreId; }
}
