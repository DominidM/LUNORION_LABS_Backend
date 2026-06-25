package com.lunorion.labs.core.orden_trabajo.application.dto.out;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrdenTrabajoResponse {
    private String id;
    private String tenantId;
    private String clienteId;
    private String vehiculoId;
    private String tecnicoId;
    private String numeroOt;
    private String estado;
    private String motivoIngreso;
    private Integer kilometrajeIngreso;
    private Integer kilometrajeSalida;
    private LocalDate fechaPrometida;
    private LocalDate fechaCierre;
    private BigDecimal totalRepuestos;
    private BigDecimal totalManoObra;
    private BigDecimal total;
    private Boolean puedeFacturar;
    private String otOrigenId;
    private String usuarioCreoId;
    private String usuarioCerroId;
    private List<OtInsumoResponse> insumos;
    private List<OtManoObraResponse> manosObra;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(String vehiculoId) { this.vehiculoId = vehiculoId; }
    public String getTecnicoId() { return tecnicoId; }
    public void setTecnicoId(String tecnicoId) { this.tecnicoId = tecnicoId; }
    public String getNumeroOt() { return numeroOt; }
    public void setNumeroOt(String numeroOt) { this.numeroOt = numeroOt; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getMotivoIngreso() { return motivoIngreso; }
    public void setMotivoIngreso(String motivoIngreso) { this.motivoIngreso = motivoIngreso; }
    public Integer getKilometrajeIngreso() { return kilometrajeIngreso; }
    public void setKilometrajeIngreso(Integer kilometrajeIngreso) { this.kilometrajeIngreso = kilometrajeIngreso; }
    public Integer getKilometrajeSalida() { return kilometrajeSalida; }
    public void setKilometrajeSalida(Integer kilometrajeSalida) { this.kilometrajeSalida = kilometrajeSalida; }
    public LocalDate getFechaPrometida() { return fechaPrometida; }
    public void setFechaPrometida(LocalDate fechaPrometida) { this.fechaPrometida = fechaPrometida; }
    public LocalDate getFechaCierre() { return fechaCierre; }
    public void setFechaCierre(LocalDate fechaCierre) { this.fechaCierre = fechaCierre; }
    public BigDecimal getTotalRepuestos() { return totalRepuestos; }
    public void setTotalRepuestos(BigDecimal totalRepuestos) { this.totalRepuestos = totalRepuestos; }
    public BigDecimal getTotalManoObra() { return totalManoObra; }
    public void setTotalManoObra(BigDecimal totalManoObra) { this.totalManoObra = totalManoObra; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public Boolean getPuedeFacturar() { return puedeFacturar; }
    public void setPuedeFacturar(Boolean puedeFacturar) { this.puedeFacturar = puedeFacturar; }
    public String getOtOrigenId() { return otOrigenId; }
    public void setOtOrigenId(String otOrigenId) { this.otOrigenId = otOrigenId; }
    public String getUsuarioCreoId() { return usuarioCreoId; }
    public void setUsuarioCreoId(String usuarioCreoId) { this.usuarioCreoId = usuarioCreoId; }
    public String getUsuarioCerroId() { return usuarioCerroId; }
    public void setUsuarioCerroId(String usuarioCerroId) { this.usuarioCerroId = usuarioCerroId; }
    public List<OtInsumoResponse> getInsumos() { return insumos; }
    public void setInsumos(List<OtInsumoResponse> insumos) { this.insumos = insumos; }
    public List<OtManoObraResponse> getManosObra() { return manosObra; }
    public void setManosObra(List<OtManoObraResponse> manosObra) { this.manosObra = manosObra; }

    public static class OtInsumoResponse {
        private String id;
        private String productoId;
        private BigDecimal cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal subtotal;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getProductoId() { return productoId; }
        public void setProductoId(String productoId) { this.productoId = productoId; }
        public BigDecimal getCantidad() { return cantidad; }
        public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }
        public BigDecimal getPrecioUnitario() { return precioUnitario; }
        public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
        public BigDecimal getSubtotal() { return subtotal; }
        public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    }

    public static class OtManoObraResponse {
        private String id;
        private String tecnicoId;
        private String servicioDescripcion;
        private BigDecimal horas;
        private BigDecimal tarifaHora;
        private BigDecimal subtotal;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getTecnicoId() { return tecnicoId; }
        public void setTecnicoId(String tecnicoId) { this.tecnicoId = tecnicoId; }
        public String getServicioDescripcion() { return servicioDescripcion; }
        public void setServicioDescripcion(String servicioDescripcion) { this.servicioDescripcion = servicioDescripcion; }
        public BigDecimal getHoras() { return horas; }
        public void setHoras(BigDecimal horas) { this.horas = horas; }
        public BigDecimal getTarifaHora() { return tarifaHora; }
        public void setTarifaHora(BigDecimal tarifaHora) { this.tarifaHora = tarifaHora; }
        public BigDecimal getSubtotal() { return subtotal; }
        public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    }
}
