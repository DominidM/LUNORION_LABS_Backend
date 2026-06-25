package com.lunorion.labs.core.checkin.application.dto.in;

public class CreateCheckinRequest {
    private String tenantId;
    private String clienteId;
    private String vehiculoId;
    private Integer kilometraje;
    private String nivelCombustible;
    private String observacionesCliente;
    private String firmaCliente;
    private String usuarioId;

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(String vehiculoId) { this.vehiculoId = vehiculoId; }
    public Integer getKilometraje() { return kilometraje; }
    public void setKilometraje(Integer kilometraje) { this.kilometraje = kilometraje; }
    public String getNivelCombustible() { return nivelCombustible; }
    public void setNivelCombustible(String nivelCombustible) { this.nivelCombustible = nivelCombustible; }
    public String getObservacionesCliente() { return observacionesCliente; }
    public void setObservacionesCliente(String observacionesCliente) { this.observacionesCliente = observacionesCliente; }
    public String getFirmaCliente() { return firmaCliente; }
    public void setFirmaCliente(String firmaCliente) { this.firmaCliente = firmaCliente; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
}
