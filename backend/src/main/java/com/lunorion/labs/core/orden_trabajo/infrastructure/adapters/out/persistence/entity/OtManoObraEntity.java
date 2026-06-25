package com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ot_mano_obra")
public class OtManoObraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "orden_trabajo_id", nullable = false)
    private UUID ordenTrabajoId;

    @Column(name = "tecnico_id")
    private UUID tecnicoId;

    @Column(name = "servicio_descripcion", columnDefinition = "TEXT")
    private String servicioDescripcion;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal horas;

    @Column(name = "tarifa_hora", nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifaHora;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public OtManoObraEntity() {}

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getOrdenTrabajoId() { return ordenTrabajoId; }
    public void setOrdenTrabajoId(UUID ordenTrabajoId) { this.ordenTrabajoId = ordenTrabajoId; }
    public UUID getTecnicoId() { return tecnicoId; }
    public void setTecnicoId(UUID tecnicoId) { this.tecnicoId = tecnicoId; }
    public String getServicioDescripcion() { return servicioDescripcion; }
    public void setServicioDescripcion(String servicioDescripcion) { this.servicioDescripcion = servicioDescripcion; }
    public BigDecimal getHoras() { return horas; }
    public void setHoras(BigDecimal horas) { this.horas = horas; }
    public BigDecimal getTarifaHora() { return tarifaHora; }
    public void setTarifaHora(BigDecimal tarifaHora) { this.tarifaHora = tarifaHora; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
