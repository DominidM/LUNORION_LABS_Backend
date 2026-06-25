package com.lunorion.labs.core.ordencompra.application.dto.out;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReporteGastosResponse {
    private String tenantId;
    private LocalDate desde;
    private LocalDate hasta;
    private BigDecimal totalGastos;
    private List<GastoItem> gastos;

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public LocalDate getDesde() { return desde; }
    public void setDesde(LocalDate desde) { this.desde = desde; }
    public LocalDate getHasta() { return hasta; }
    public void setHasta(LocalDate hasta) { this.hasta = hasta; }
    public BigDecimal getTotalGastos() { return totalGastos; }
    public void setTotalGastos(BigDecimal totalGastos) { this.totalGastos = totalGastos; }
    public List<GastoItem> getGastos() { return gastos; }
    public void setGastos(List<GastoItem> gastos) { this.gastos = gastos; }

    public static class GastoItem {
        private String ordenCompraId;
        private String numeroOrden;
        private String proveedorId;
        private LocalDate fechaEmision;
        private BigDecimal total;
        private String estado;

        public String getOrdenCompraId() { return ordenCompraId; }
        public void setOrdenCompraId(String ordenCompraId) { this.ordenCompraId = ordenCompraId; }
        public String getNumeroOrden() { return numeroOrden; }
        public void setNumeroOrden(String numeroOrden) { this.numeroOrden = numeroOrden; }
        public String getProveedorId() { return proveedorId; }
        public void setProveedorId(String proveedorId) { this.proveedorId = proveedorId; }
        public LocalDate getFechaEmision() { return fechaEmision; }
        public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }
        public BigDecimal getTotal() { return total; }
        public void setTotal(BigDecimal total) { this.total = total; }
        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
    }
}
