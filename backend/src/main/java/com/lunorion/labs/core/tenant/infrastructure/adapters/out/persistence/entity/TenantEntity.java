package com.lunorion.labs.core.tenant.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tenant")
public class TenantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, length = 11)
    private String ruc;

    @Column(name = "razon_social", nullable = false, length = 200)
    private String razonSocial;

    @Column(name = "nombre_comercial", length = 200)
    private String nombreComercial;

    @Column(name = "domicilio_fiscal", length = 300)
    private String domicilioFiscal;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String telefono;

    @Column(name = "regimen_tributario", length = 30)
    private String regimenTributario;

    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @Column(name = "color_primario", length = 7)
    private String colorPrimario;

    @Column(name = "color_secundario", length = 7)
    private String colorSecundario;

    @Column(length = 30)
    private String plan;

    @Column(length = 20)
    private String estado;

    @Column(name = "certificado_p12")
    private byte[] certificadoP12;

    @Column(name = "certificado_password")
    private String certificadoPassword;

    @Column(name = "certificado_validez")
    private LocalDate certificadoValidez;

    @Column(name = "ruc_validado_sunat")
    private Boolean rucValidadoSunat;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public TenantEntity() {}

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
    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }
    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }
    public String getDomicilioFiscal() { return domicilioFiscal; }
    public void setDomicilioFiscal(String domicilioFiscal) { this.domicilioFiscal = domicilioFiscal; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getRegimenTributario() { return regimenTributario; }
    public void setRegimenTributario(String regimenTributario) { this.regimenTributario = regimenTributario; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public String getColorPrimario() { return colorPrimario; }
    public void setColorPrimario(String colorPrimario) { this.colorPrimario = colorPrimario; }
    public String getColorSecundario() { return colorSecundario; }
    public void setColorSecundario(String colorSecundario) { this.colorSecundario = colorSecundario; }
    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public byte[] getCertificadoP12() { return certificadoP12; }
    public void setCertificadoP12(byte[] certificadoP12) { this.certificadoP12 = certificadoP12; }
    public String getCertificadoPassword() { return certificadoPassword; }
    public void setCertificadoPassword(String certificadoPassword) { this.certificadoPassword = certificadoPassword; }
    public LocalDate getCertificadoValidez() { return certificadoValidez; }
    public void setCertificadoValidez(LocalDate certificadoValidez) { this.certificadoValidez = certificadoValidez; }
    public Boolean getRucValidadoSunat() { return rucValidadoSunat; }
    public void setRucValidadoSunat(Boolean rucValidadoSunat) { this.rucValidadoSunat = rucValidadoSunat; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
