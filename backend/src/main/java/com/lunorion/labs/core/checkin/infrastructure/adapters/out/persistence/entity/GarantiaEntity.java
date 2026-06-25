package com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "garantia")
public class GarantiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "ot_original_id")
    private UUID otOriginalId;

    @Column(name = "ot_garantia_id")
    private UUID otGarantiaId;

    @Column(columnDefinition = "TEXT")
    private String motivo;

    @Column(name = "costo_repuestos", precision = 10, scale = 2)
    private BigDecimal costoRepuestos;

    @Column(name = "costo_mano_obra", precision = 10, scale = 2)
    private BigDecimal costoManoObra;

    @Column(name = "costo_total", precision = 10, scale = 2)
    private BigDecimal costoTotal;

    @Column(name = "usuario_id")
    private UUID usuarioId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public GarantiaEntity() {}

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getOtOriginalId() { return otOriginalId; }
    public void setOtOriginalId(UUID otOriginalId) { this.otOriginalId = otOriginalId; }
    public UUID getOtGarantiaId() { return otGarantiaId; }
    public void setOtGarantiaId(UUID otGarantiaId) { this.otGarantiaId = otGarantiaId; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public BigDecimal getCostoRepuestos() { return costoRepuestos; }
    public void setCostoRepuestos(BigDecimal costoRepuestos) { this.costoRepuestos = costoRepuestos; }
    public BigDecimal getCostoManoObra() { return costoManoObra; }
    public void setCostoManoObra(BigDecimal costoManoObra) { this.costoManoObra = costoManoObra; }
    public BigDecimal getCostoTotal() { return costoTotal; }
    public void setCostoTotal(BigDecimal costoTotal) { this.costoTotal = costoTotal; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
