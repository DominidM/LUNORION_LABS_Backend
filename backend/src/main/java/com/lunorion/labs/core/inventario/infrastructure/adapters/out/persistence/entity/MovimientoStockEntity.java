package com.lunorion.labs.core.inventario.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movimiento_stock")
public class MovimientoStockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "producto_id", nullable = false)
    private UUID productoId;

    @Column(nullable = false, length = 20)
    private String tipo;

    @Column(length = 30)
    private String subtipo;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "costo_unitario", precision = 12, scale = 2)
    private BigDecimal costoUnitario;

    @Column(name = "stock_anterior", precision = 12, scale = 2)
    private BigDecimal stockAnterior;

    @Column(name = "stock_posterior", precision = 12, scale = 2)
    private BigDecimal stockPosterior;

    @Column(name = "documento_origen", length = 100)
    private String documentoOrigen;

    @Column(columnDefinition = "TEXT")
    private String observacion;

    @Column(name = "usuario_id")
    private UUID usuarioId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public MovimientoStockEntity() {}

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
    public UUID getProductoId() { return productoId; }
    public void setProductoId(UUID productoId) { this.productoId = productoId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getSubtipo() { return subtipo; }
    public void setSubtipo(String subtipo) { this.subtipo = subtipo; }
    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }
    public BigDecimal getCostoUnitario() { return costoUnitario; }
    public void setCostoUnitario(BigDecimal costoUnitario) { this.costoUnitario = costoUnitario; }
    public BigDecimal getStockAnterior() { return stockAnterior; }
    public void setStockAnterior(BigDecimal stockAnterior) { this.stockAnterior = stockAnterior; }
    public BigDecimal getStockPosterior() { return stockPosterior; }
    public void setStockPosterior(BigDecimal stockPosterior) { this.stockPosterior = stockPosterior; }
    public String getDocumentoOrigen() { return documentoOrigen; }
    public void setDocumentoOrigen(String documentoOrigen) { this.documentoOrigen = documentoOrigen; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
