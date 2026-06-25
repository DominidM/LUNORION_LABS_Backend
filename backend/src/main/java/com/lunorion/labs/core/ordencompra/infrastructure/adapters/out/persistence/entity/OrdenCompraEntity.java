package com.lunorion.labs.core.ordencompra.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orden_compra")
public class OrdenCompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "proveedor_id", nullable = false)
    private UUID proveedorId;

    @Column(name = "numero_orden", unique = true, nullable = false, length = 50)
    private String numeroOrden;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @Column(columnDefinition = "TEXT")
    private String observacion;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public OrdenCompraEntity() {}

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getProveedorId() { return proveedorId; }
    public void setProveedorId(UUID proveedorId) { this.proveedorId = proveedorId; }
    public String getNumeroOrden() { return numeroOrden; }
    public void setNumeroOrden(String numeroOrden) { this.numeroOrden = numeroOrden; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
