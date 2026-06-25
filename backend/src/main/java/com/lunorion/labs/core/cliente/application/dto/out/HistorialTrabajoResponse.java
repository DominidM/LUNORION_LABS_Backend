package com.lunorion.labs.core.cliente.application.dto.out;

import java.time.LocalDateTime;

public class HistorialTrabajoResponse {
    private String id;
    private String ordenTrabajoId;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getOrdenTrabajoId() { return ordenTrabajoId; }
    public void setOrdenTrabajoId(String ordenTrabajoId) { this.ordenTrabajoId = ordenTrabajoId; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
