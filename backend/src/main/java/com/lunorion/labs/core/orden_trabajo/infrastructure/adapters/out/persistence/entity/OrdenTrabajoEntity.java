package com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orden_trabajo")
public class OrdenTrabajoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "cliente_id")
    private UUID clienteId;

    @Column(name = "vehiculo_id")
    private UUID vehiculoId;

    @Column(name = "tecnico_id")
    private UUID tecnicoId;

    @Column(name = "numero_ot", nullable = false, length = 30)
    private String numeroOt;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(name = "motivo_ingreso", columnDefinition = "TEXT")
    private String motivoIngreso;

    @Column(name = "kilometraje_ingreso")
    private Integer kilometrajeIngreso;

    @Column(name = "fecha_prometida")
    private LocalDate fechaPrometida;

    @Column(name = "total_repuestos", precision = 12, scale = 2)
    private BigDecimal totalRepuestos;

    @Column(name = "total_mano_obra", precision = 12, scale = 2)
    private BigDecimal totalManoObra;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @Column(name = "ot_origen_id")
    private UUID otOrigenId;

    @Column(name = "usuario_creo_id")
    private UUID usuarioCreoId;

    @Column(name = "usuario_cerro_id")
    private UUID usuarioCerroId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public OrdenTrabajoEntity() {}

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getClienteId() { return clienteId; }
    public void setClienteId(UUID clienteId) { this.clienteId = clienteId; }
    public UUID getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(UUID vehiculoId) { this.vehiculoId = vehiculoId; }
    public UUID getTecnicoId() { return tecnicoId; }
    public void setTecnicoId(UUID tecnicoId) { this.tecnicoId = tecnicoId; }
    public String getNumeroOt() { return numeroOt; }
    public void setNumeroOt(String numeroOt) { this.numeroOt = numeroOt; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getMotivoIngreso() { return motivoIngreso; }
    public void setMotivoIngreso(String motivoIngreso) { this.motivoIngreso = motivoIngreso; }
    public Integer getKilometrajeIngreso() { return kilometrajeIngreso; }
    public void setKilometrajeIngreso(Integer kilometrajeIngreso) { this.kilometrajeIngreso = kilometrajeIngreso; }
    public LocalDate getFechaPrometida() { return fechaPrometida; }
    public void setFechaPrometida(LocalDate fechaPrometida) { this.fechaPrometida = fechaPrometida; }
    public BigDecimal getTotalRepuestos() { return totalRepuestos; }
    public void setTotalRepuestos(BigDecimal totalRepuestos) { this.totalRepuestos = totalRepuestos; }
    public BigDecimal getTotalManoObra() { return totalManoObra; }
    public void setTotalManoObra(BigDecimal totalManoObra) { this.totalManoObra = totalManoObra; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public UUID getOtOrigenId() { return otOrigenId; }
    public void setOtOrigenId(UUID otOrigenId) { this.otOrigenId = otOrigenId; }
    public UUID getUsuarioCreoId() { return usuarioCreoId; }
    public void setUsuarioCreoId(UUID usuarioCreoId) { this.usuarioCreoId = usuarioCreoId; }
    public UUID getUsuarioCerroId() { return usuarioCerroId; }
    public void setUsuarioCerroId(UUID usuarioCerroId) { this.usuarioCerroId = usuarioCerroId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
