package com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "comprobante_electronico")
public class ComprobanteElectronicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "venta_id")
    private String ventaId;

    @Column(nullable = false, length = 2)
    private String tipo;

    @Column(nullable = false, length = 4)
    private String serie;

    @Column(nullable = false)
    private int numero;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "hora_emision")
    private LocalTime horaEmision;

    @Column(name = "xml_firmado", columnDefinition = "TEXT")
    private String xmlFirmado;

    @Column(name = "xml_cdr", columnDefinition = "TEXT")
    private String xmlCdr;

    @Column(name = "hash_firma", length = 64)
    private String hashFirma;

    @Column(name = "estado_sunat", nullable = false, length = 20)
    private String estadoSunat;

    @Column(name = "codigo_error_sunat", length = 10)
    private String codigoErrorSunat;

    @Column(name = "descripcion_error", columnDefinition = "TEXT")
    private String descripcionError;

    @Column(name = "comprobante_referencia_id")
    private String comprobanteReferenciaId;

    @Column(name = "monto_operaciones_gravadas", precision = 12, scale = 2)
    private BigDecimal montoOperacionesGravadas;

    @Column(name = "monto_igv", precision = 12, scale = 2)
    private BigDecimal montoIgv;

    @Column(name = "monto_total", precision = 12, scale = 2)
    private BigDecimal montoTotal;

    @Column(name = "ruc_cliente", length = 11)
    private String rucCliente;

    @Column(name = "razon_social_cliente", length = 200)
    private String razonSocialCliente;

    @Column(name = "intentos_envio")
    private int intentosEnvio;

    @Column(name = "ultimo_envio")
    private LocalTime ultimoEnvio;

    @Column(name = "enviado_por_id")
    private String enviadoPorId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public ComprobanteElectronicoEntity() {}

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public String getVentaId() { return ventaId; }
    public void setVentaId(String ventaId) { this.ventaId = ventaId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }
    public LocalTime getHoraEmision() { return horaEmision; }
    public void setHoraEmision(LocalTime horaEmision) { this.horaEmision = horaEmision; }
    public String getXmlFirmado() { return xmlFirmado; }
    public void setXmlFirmado(String xmlFirmado) { this.xmlFirmado = xmlFirmado; }
    public String getXmlCdr() { return xmlCdr; }
    public void setXmlCdr(String xmlCdr) { this.xmlCdr = xmlCdr; }
    public String getHashFirma() { return hashFirma; }
    public void setHashFirma(String hashFirma) { this.hashFirma = hashFirma; }
    public String getEstadoSunat() { return estadoSunat; }
    public void setEstadoSunat(String estadoSunat) { this.estadoSunat = estadoSunat; }
    public String getCodigoErrorSunat() { return codigoErrorSunat; }
    public void setCodigoErrorSunat(String codigoErrorSunat) { this.codigoErrorSunat = codigoErrorSunat; }
    public String getDescripcionError() { return descripcionError; }
    public void setDescripcionError(String descripcionError) { this.descripcionError = descripcionError; }
    public String getComprobanteReferenciaId() { return comprobanteReferenciaId; }
    public void setComprobanteReferenciaId(String comprobanteReferenciaId) { this.comprobanteReferenciaId = comprobanteReferenciaId; }
    public BigDecimal getMontoOperacionesGravadas() { return montoOperacionesGravadas; }
    public void setMontoOperacionesGravadas(BigDecimal montoOperacionesGravadas) { this.montoOperacionesGravadas = montoOperacionesGravadas; }
    public BigDecimal getMontoIgv() { return montoIgv; }
    public void setMontoIgv(BigDecimal montoIgv) { this.montoIgv = montoIgv; }
    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }
    public String getRucCliente() { return rucCliente; }
    public void setRucCliente(String rucCliente) { this.rucCliente = rucCliente; }
    public String getRazonSocialCliente() { return razonSocialCliente; }
    public void setRazonSocialCliente(String razonSocialCliente) { this.razonSocialCliente = razonSocialCliente; }
    public int getIntentosEnvio() { return intentosEnvio; }
    public void setIntentosEnvio(int intentosEnvio) { this.intentosEnvio = intentosEnvio; }
    public LocalTime getUltimoEnvio() { return ultimoEnvio; }
    public void setUltimoEnvio(LocalTime ultimoEnvio) { this.ultimoEnvio = ultimoEnvio; }
    public String getEnviadoPorId() { return enviadoPorId; }
    public void setEnviadoPorId(String enviadoPorId) { this.enviadoPorId = enviadoPorId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
