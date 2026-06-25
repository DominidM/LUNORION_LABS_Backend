package com.lunorion.labs.core.tecnico.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class ConfiguracionComision {
    private UUID id;
    private String tenantId;
    private String tecnicoId;
    private String productoId;
    private TipoComision tipo;
    private BigDecimal porcentaje;
    private boolean activo;

    public ConfiguracionComision() {}

    public ConfiguracionComision(UUID id, String tenantId, String tecnicoId, String productoId, TipoComision tipo, BigDecimal porcentaje) {
        this.id = id;
        this.tenantId = tenantId;
        this.tecnicoId = tecnicoId;
        this.productoId = productoId;
        this.tipo = tipo;
        this.porcentaje = porcentaje;
        this.activo = true;
    }

    public static ConfiguracionComision create(String tenantId, String tecnicoId, String productoId, TipoComision tipo, BigDecimal porcentaje) {
        return new ConfiguracionComision(UUID.randomUUID(), tenantId, tecnicoId, productoId, tipo, porcentaje);
    }

    public void desactivar() { this.activo = false; }

    public UUID getId() { return id; }
    public String getTenantId() { return tenantId; }
    public String getTecnicoId() { return tecnicoId; }
    public String getProductoId() { return productoId; }
    public TipoComision getTipo() { return tipo; }
    public BigDecimal getPorcentaje() { return porcentaje; }
    public boolean isActivo() { return activo; }
}
