package com.lunorion.labs.core.planilla.application.dto.out;

import java.time.LocalDateTime;

public class PlameResponse {
    private String id;
    private String tenantId;
    private String periodo;
    private String archivoGenerado;
    private LocalDateTime fechaGeneracion;
    private String estado;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    public String getArchivoGenerado() { return archivoGenerado; }
    public void setArchivoGenerado(String archivoGenerado) { this.archivoGenerado = archivoGenerado; }
    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
