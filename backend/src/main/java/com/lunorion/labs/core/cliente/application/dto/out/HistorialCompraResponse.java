package com.lunorion.labs.core.cliente.application.dto.out;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HistorialCompraResponse {
    private String id;
    private String ventaId;
    private BigDecimal total;
    private LocalDateTime fecha;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getVentaId() { return ventaId; }
    public void setVentaId(String ventaId) { this.ventaId = ventaId; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
