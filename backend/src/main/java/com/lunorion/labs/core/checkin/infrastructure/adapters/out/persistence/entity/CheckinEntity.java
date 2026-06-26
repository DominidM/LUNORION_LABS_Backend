package com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "checkin")
public class CheckinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "cliente_id")
    private UUID clienteId;

    @Column(name = "vehiculo_id")
    private UUID vehiculoId;

    private Integer kilometraje;

    @Column(name = "nivel_combustible", length = 50)
    private String nivelCombustible;

    @Column(name = "observaciones_cliente", columnDefinition = "TEXT")
    private String observacionesCliente;

    @Column(name = "firma_cliente", columnDefinition = "TEXT")
    private String firmaCliente;

    @Column(name = "pdf_acta", columnDefinition = "TEXT")
    private String pdfActa;

    @Column(name = "ot_id")
    private UUID otId;

    @Column(name = "usuario_id")
    private UUID usuarioId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public CheckinEntity() {}

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getClienteId() { return clienteId; }
    public void setClienteId(UUID clienteId) { this.clienteId = clienteId; }
    public UUID getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(UUID vehiculoId) { this.vehiculoId = vehiculoId; }
    public Integer getKilometraje() { return kilometraje; }
    public void setKilometraje(Integer kilometraje) { this.kilometraje = kilometraje; }
    public String getNivelCombustible() { return nivelCombustible; }
    public void setNivelCombustible(String nivelCombustible) { this.nivelCombustible = nivelCombustible; }
    public String getObservacionesCliente() { return observacionesCliente; }
    public void setObservacionesCliente(String observacionesCliente) { this.observacionesCliente = observacionesCliente; }
    public String getFirmaCliente() { return firmaCliente; }
    public void setFirmaCliente(String firmaCliente) { this.firmaCliente = firmaCliente; }
    public String getPdfActa() { return pdfActa; }
    public void setPdfActa(String pdfActa) { this.pdfActa = pdfActa; }
    public UUID getOtId() { return otId; }
    public void setOtId(UUID otId) { this.otId = otId; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
