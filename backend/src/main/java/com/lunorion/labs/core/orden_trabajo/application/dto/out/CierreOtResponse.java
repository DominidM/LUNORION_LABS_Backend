package com.lunorion.labs.core.orden_trabajo.application.dto.out;

import java.math.BigDecimal;

public class CierreOtResponse {
    private String id;
    private BigDecimal totalRepuestos;
    private BigDecimal totalManoObra;
    private BigDecimal total;
    private Boolean puedeFacturar;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public BigDecimal getTotalRepuestos() { return totalRepuestos; }
    public void setTotalRepuestos(BigDecimal totalRepuestos) { this.totalRepuestos = totalRepuestos; }
    public BigDecimal getTotalManoObra() { return totalManoObra; }
    public void setTotalManoObra(BigDecimal totalManoObra) { this.totalManoObra = totalManoObra; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public Boolean getPuedeFacturar() { return puedeFacturar; }
    public void setPuedeFacturar(Boolean puedeFacturar) { this.puedeFacturar = puedeFacturar; }
}
