package com.lunorion.labs.core.tecnico.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Tecnico extends BaseEntity {
    private String tenantId;
    private String usuarioId;
    private String especialidades;
    private BigDecimal tarifaHora;
    private String numeroContacto;
    private LocalDate fechaIngreso;
    private boolean activo;

    public Tecnico() {}

    public Tecnico(UUID id, String tenantId, String usuarioId, String especialidades, BigDecimal tarifaHora, String numeroContacto) {
        super(id);
        this.tenantId = tenantId;
        this.usuarioId = usuarioId;
        this.especialidades = especialidades;
        this.tarifaHora = tarifaHora;
        this.numeroContacto = numeroContacto;
        this.fechaIngreso = LocalDate.now();
        this.activo = true;
    }

    public static Tecnico create(String tenantId, String usuarioId, String especialidades, BigDecimal tarifaHora, String numeroContacto) {
        return new Tecnico(UUID.randomUUID(), tenantId, usuarioId, especialidades, tarifaHora, numeroContacto);
    }

    public void desactivar() {
        this.activo = false;
        markUpdated();
    }

    public void actualizarTarifa(BigDecimal tarifa) {
        this.tarifaHora = tarifa;
        markUpdated();
    }

    public String getTenantId() { return tenantId; }
    public String getUsuarioId() { return usuarioId; }
    public String getEspecialidades() { return especialidades; }
    public BigDecimal getTarifaHora() { return tarifaHora; }
    public String getNumeroContacto() { return numeroContacto; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public boolean isActivo() { return activo; }
}
