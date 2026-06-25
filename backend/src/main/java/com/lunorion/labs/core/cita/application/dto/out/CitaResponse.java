package com.lunorion.labs.core.cita.application.dto.out;

public class CitaResponse {
    private String id;
    private String tenantId;
    private String clienteId;
    private String vehiculoId;
    private String tecnicoId;
    private String servicioDescripcion;
    private String fechaHora;
    private int duracionMinutos;
    private String estado;
    private boolean recordatorioEnviado;
    private boolean notificarWhatsapp;
    private String usuarioCreoId;

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
    public String getServicioDescripcion() { return servicioDescripcion; }
    public void setServicioDescripcion(String servicioDescripcion) { this.servicioDescripcion = servicioDescripcion; }
    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public boolean isRecordatorioEnviado() { return recordatorioEnviado; }
    public void setRecordatorioEnviado(boolean recordatorioEnviado) { this.recordatorioEnviado = recordatorioEnviado; }
    public boolean isNotificarWhatsapp() { return notificarWhatsapp; }
    public void setNotificarWhatsapp(boolean notificarWhatsapp) { this.notificarWhatsapp = notificarWhatsapp; }
    public String getUsuarioCreoId() { return usuarioCreoId; }
    public void setUsuarioCreoId(String usuarioCreoId) { this.usuarioCreoId = usuarioCreoId; }
}
