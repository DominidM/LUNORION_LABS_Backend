package com.lunorion.labs.core.comprobante_electronico.application.dto.out;

public class PleResponse {
    private String id;
    private String periodo;
    private String estado;
    private String archivoUrl;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getArchivoUrl() { return archivoUrl; }
    public void setArchivoUrl(String archivoUrl) { this.archivoUrl = archivoUrl; }
}
