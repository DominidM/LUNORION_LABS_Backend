package com.lunorion.labs.core.comprobante_electronico.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;

import java.time.LocalDate;
import java.util.UUID;

public class ResumenDiario extends BaseEntity {

    private String tenantId;
    private LocalDate fechaResumen;
    private String codigoResumen;
    private String xmlFirmado;
    private String xmlCdr;
    private String estado;
    private int totalBoletas;
    private int totalAnulaciones;

    public ResumenDiario() {}

    public ResumenDiario(UUID id, String tenantId, LocalDate fechaResumen, String codigoResumen) {
        super(id);
        this.tenantId = tenantId;
        this.fechaResumen = fechaResumen;
        this.codigoResumen = codigoResumen;
        this.estado = "PENDIENTE";
    }

    public static ResumenDiario create(String tenantId, LocalDate fechaResumen, String codigoResumen) {
        return new ResumenDiario(UUID.randomUUID(), tenantId, fechaResumen, codigoResumen);
    }

    public void enviar() {
        this.estado = "ENVIADO";
        markUpdated();
    }

    public void aceptar() {
        this.estado = "ACEPTADO";
        markUpdated();
    }

    public void rechazar() {
        this.estado = "RECHAZADO";
        markUpdated();
    }

    public String getTenantId() { return tenantId; }
    public LocalDate getFechaResumen() { return fechaResumen; }
    public String getCodigoResumen() { return codigoResumen; }
    public String getXmlFirmado() { return xmlFirmado; }
    public void setXmlFirmado(String xmlFirmado) { this.xmlFirmado = xmlFirmado; }
    public String getXmlCdr() { return xmlCdr; }
    public void setXmlCdr(String xmlCdr) { this.xmlCdr = xmlCdr; }
    public String getEstado() { return estado; }
    public int getTotalBoletas() { return totalBoletas; }
    public void setTotalBoletas(int totalBoletas) { this.totalBoletas = totalBoletas; }
    public int getTotalAnulaciones() { return totalAnulaciones; }
    public void setTotalAnulaciones(int totalAnulaciones) { this.totalAnulaciones = totalAnulaciones; }
}
