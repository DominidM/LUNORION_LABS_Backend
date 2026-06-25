package com.lunorion.labs.core.comprobante_electronico.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class ComprobanteElectronico extends BaseEntity {

    private String tenantId;
    private String ventaId;
    private String tipo;
    private String serie;
    private int numero;
    private LocalDate fechaEmision;
    private LocalTime horaEmision;
    private String xmlFirmado;
    private String xmlCdr;
    private String hashFirma;
    private String estadoSunat;
    private String codigoErrorSunat;
    private String descripcionError;
    private String comprobanteReferenciaId;
    private BigDecimal montoOperacionesGravadas;
    private BigDecimal montoIgv;
    private BigDecimal montoTotal;
    private String rucCliente;
    private String razonSocialCliente;
    private int intentosEnvio;
    private LocalTime ultimoEnvio;
    private String enviadoPorId;

    public ComprobanteElectronico() {}

    public ComprobanteElectronico(UUID id, String tenantId, String ventaId, String tipo, String serie, int numero,
                                  LocalDate fechaEmision, LocalTime horaEmision, BigDecimal montoTotal,
                                  String rucCliente, String razonSocialCliente, String enviadoPorId) {
        super(id);
        this.tenantId = tenantId;
        this.ventaId = ventaId;
        this.tipo = tipo;
        this.serie = serie;
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.horaEmision = horaEmision;
        this.montoTotal = montoTotal;
        this.rucCliente = rucCliente;
        this.razonSocialCliente = razonSocialCliente;
        this.enviadoPorId = enviadoPorId;
        this.estadoSunat = "PENDIENTE";
        this.intentosEnvio = 0;
    }

    public static ComprobanteElectronico create(String tenantId, String ventaId, String tipo, String serie, int numero,
                                                LocalDate fechaEmision, LocalTime horaEmision, BigDecimal montoTotal,
                                                String rucCliente, String razonSocialCliente, String enviadoPorId) {
        return new ComprobanteElectronico(UUID.randomUUID(), tenantId, ventaId, tipo, serie, numero,
                fechaEmision, horaEmision, montoTotal, rucCliente, razonSocialCliente, enviadoPorId);
    }

    public void firmar() {
        this.xmlFirmado = "<firmado/>";
        markUpdated();
    }

    public void enviarSunat() {
        this.intentosEnvio++;
        this.ultimoEnvio = LocalTime.now();
        markUpdated();
    }

    public void aceptar() {
        this.estadoSunat = "ACEPTADO";
        this.codigoErrorSunat = null;
        this.descripcionError = null;
        markUpdated();
    }

    public void rechazar(String error) {
        this.estadoSunat = "RECHAZADO";
        this.codigoErrorSunat = "ERROR";
        this.descripcionError = error;
        markUpdated();
    }

    public String getTenantId() { return tenantId; }
    public String getVentaId() { return ventaId; }
    public String getTipo() { return tipo; }
    public String getSerie() { return serie; }
    public int getNumero() { return numero; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public LocalTime getHoraEmision() { return horaEmision; }
    public String getXmlFirmado() { return xmlFirmado; }
    public void setXmlFirmado(String xmlFirmado) { this.xmlFirmado = xmlFirmado; }
    public String getXmlCdr() { return xmlCdr; }
    public void setXmlCdr(String xmlCdr) { this.xmlCdr = xmlCdr; }
    public String getHashFirma() { return hashFirma; }
    public void setHashFirma(String hashFirma) { this.hashFirma = hashFirma; }
    public String getEstadoSunat() { return estadoSunat; }
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
    public String getRucCliente() { return rucCliente; }
    public String getRazonSocialCliente() { return razonSocialCliente; }
    public int getIntentosEnvio() { return intentosEnvio; }
    public LocalTime getUltimoEnvio() { return ultimoEnvio; }
    public String getEnviadoPorId() { return enviadoPorId; }
}
