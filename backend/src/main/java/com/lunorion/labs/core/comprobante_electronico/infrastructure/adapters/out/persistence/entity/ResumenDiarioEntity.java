package com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "resumen_diario")
public class ResumenDiarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "fecha_resumen")
    private LocalDate fechaResumen;

    @Column(name = "codigo_resumen", length = 10)
    private String codigoResumen;

    @Column(name = "xml_firmado", columnDefinition = "TEXT")
    private String xmlFirmado;

    @Column(name = "xml_cdr", columnDefinition = "TEXT")
    private String xmlCdr;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(name = "total_boletas")
    private int totalBoletas;

    @Column(name = "total_anulaciones")
    private int totalAnulaciones;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public ResumenDiarioEntity() {}

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public LocalDate getFechaResumen() { return fechaResumen; }
    public void setFechaResumen(LocalDate fechaResumen) { this.fechaResumen = fechaResumen; }
    public String getCodigoResumen() { return codigoResumen; }
    public void setCodigoResumen(String codigoResumen) { this.codigoResumen = codigoResumen; }
    public String getXmlFirmado() { return xmlFirmado; }
    public void setXmlFirmado(String xmlFirmado) { this.xmlFirmado = xmlFirmado; }
    public String getXmlCdr() { return xmlCdr; }
    public void setXmlCdr(String xmlCdr) { this.xmlCdr = xmlCdr; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public int getTotalBoletas() { return totalBoletas; }
    public void setTotalBoletas(int totalBoletas) { this.totalBoletas = totalBoletas; }
    public int getTotalAnulaciones() { return totalAnulaciones; }
    public void setTotalAnulaciones(int totalAnulaciones) { this.totalAnulaciones = totalAnulaciones; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
