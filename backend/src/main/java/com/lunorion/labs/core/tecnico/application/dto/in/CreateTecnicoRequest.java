package com.lunorion.labs.core.tecnico.application.dto.in;

import java.math.BigDecimal;

public class CreateTecnicoRequest {
    private String tenantId;
    private String usuarioId;
    private String especialidades;
    private BigDecimal tarifaHora;
    private String numeroContacto;

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public String getEspecialidades() { return especialidades; }
    public void setEspecialidades(String especialidades) { this.especialidades = especialidades; }
    public BigDecimal getTarifaHora() { return tarifaHora; }
    public void setTarifaHora(BigDecimal tarifaHora) { this.tarifaHora = tarifaHora; }
    public String getNumeroContacto() { return numeroContacto; }
    public void setNumeroContacto(String numeroContacto) { this.numeroContacto = numeroContacto; }
}
