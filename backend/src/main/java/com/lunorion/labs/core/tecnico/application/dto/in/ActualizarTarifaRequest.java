package com.lunorion.labs.core.tecnico.application.dto.in;

import java.math.BigDecimal;

public class ActualizarTarifaRequest {
    private BigDecimal tarifa;

    public BigDecimal getTarifa() { return tarifa; }
    public void setTarifa(BigDecimal tarifa) { this.tarifa = tarifa; }
}
