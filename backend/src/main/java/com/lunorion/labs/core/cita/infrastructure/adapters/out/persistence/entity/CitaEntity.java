package com.lunorion.labs.core.cita.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cita")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "cliente_id")
    private String clienteId;

    @Column(name = "vehiculo_id")
    private String vehiculoId;

    @Column(name = "tecnico_id")
    private String tecnicoId;

    @Column(name = "servicio_descripcion", columnDefinition = "TEXT")
    private String servicioDescripcion;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "duracion_minutos")
    private int duracionMinutos;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(name = "recordatorio_enviado")
    private Boolean recordatorioEnviado = false;

    @Column(name = "notificar_whatsapp")
    private Boolean notificarWhatsapp = false;

    @Column(name = "usuario_creo")
    private String usuarioCreoId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public CitaEntity() {}

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(String vehiculoId) { this.vehiculoId = vehiculoId; }
    public String getTecnicoId() { return tecnicoId; }
    public void setTecnicoId(String tecnicoId) { this.tecnicoId = tecnicoId; }
    public String getServicioDescripcion() { return servicioDescripcion; }
    public void setServicioDescripcion(String servicioDescripcion) { this.servicioDescripcion = servicioDescripcion; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Boolean getRecordatorioEnviado() { return recordatorioEnviado; }
    public void setRecordatorioEnviado(Boolean recordatorioEnviado) { this.recordatorioEnviado = recordatorioEnviado; }
    public Boolean getNotificarWhatsapp() { return notificarWhatsapp; }
    public void setNotificarWhatsapp(Boolean notificarWhatsapp) { this.notificarWhatsapp = notificarWhatsapp; }
    public String getUsuarioCreoId() { return usuarioCreoId; }
    public void setUsuarioCreoId(String usuarioCreoId) { this.usuarioCreoId = usuarioCreoId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
