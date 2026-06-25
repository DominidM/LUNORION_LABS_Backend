package com.lunorion.labs.core.comprobante_electronico.application.dto.out;

public class CdrResponse {
    private String codigo;
    private String descripcion;
    private String fechaRecepcion;

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getFechaRecepcion() { return fechaRecepcion; }
    public void setFechaRecepcion(String fechaRecepcion) { this.fechaRecepcion = fechaRecepcion; }
}
