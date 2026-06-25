package com.lunorion.labs.core.comprobante_electronico.application.dto.out;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class ComprobanteResponse {
    private String id;
    private String tenantId;
    private String ventaId;
    private String tipo;
    private String serie;
    private int numero;
    private String fechaEmision;
    private String horaEmision;
    private String xmlFirmado;
    private String xmlCdr;
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
    private String ultimoEnvio;
    private String enviadoPorId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getVentaId() { return ventaId; }
    public void setVentaId(String ventaId) { this.ventaId = ventaId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public String getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(String fechaEmision) { this.fechaEmision = fechaEmision; }
    public String getHoraEmision() { return horaEmision; }
    public void setHoraEmision(String horaEmision) { this.horaEmision = horaEmision; }
    public String getXmlFirmado() { return xmlFirmado; }
    public void setXmlFirmado(String xmlFirmado) { this.xmlFirmado = xmlFirmado; }
    public String getXmlCdr() { return xmlCdr; }
    public void setXmlCdr(String xmlCdr) { this.xmlCdr = xmlCdr; }
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
    public String getUltimoEnvio() { return ultimoEnvio; }
    public void setUltimoEnvio(String ultimoEnvio) { this.ultimoEnvio = ultimoEnvio; }
    public String getEnviadoPorId() { return enviadoPorId; }
    public void setEnviadoPorId(String enviadoPorId) { this.enviadoPorId = enviadoPorId; }
}
