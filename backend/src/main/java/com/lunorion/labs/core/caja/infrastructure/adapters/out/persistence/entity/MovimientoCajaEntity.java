package com.lunorion.labs.core.caja.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movimiento_caja")
public class MovimientoCajaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "cierre_caja_id")
    private UUID cierreCajaId;

    @Column(nullable = false, length = 10)
    private String tipo;

    @Column(name = "metodo_pago", length = 30)
    private String metodoPago;

    @Column(precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(length = 100)
    private String referencia;

    @Column(columnDefinition = "TEXT")
    private String concepto;

    @Column(name = "usuario_id")
    private String usuarioId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public MovimientoCajaEntity() {}

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getCierreCajaId() { return cierreCajaId; }
    public void setCierreCajaId(UUID cierreCajaId) { this.cierreCajaId = cierreCajaId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
