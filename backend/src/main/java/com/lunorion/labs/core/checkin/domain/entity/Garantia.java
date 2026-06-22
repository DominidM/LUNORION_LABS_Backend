package com.lunorion.labs.core.checkin.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Garantia {
    private UUID id;
    private String tenantId;
    private String otOriginalId;
    private String otGarantiaId;
    private String motivo;
    private BigDecimal costoRepuestos;
    private BigDecimal costoManoObra;
    private BigDecimal costoTotal;
    private String usuarioId;

    public Garantia() {}

    public Garantia(UUID id, String tenantId, String otOriginalId, String motivo,
                    BigDecimal costoRepuestos, BigDecimal costoManoObra, String usuarioId) {
        this.id = id;
        this.tenantId = tenantId;
        this.otOriginalId = otOriginalId;
        this.motivo = motivo;
        this.costoRepuestos = costoRepuestos;
        this.costoManoObra = costoManoObra;
        this.costoTotal = costoRepuestos.add(costoManoObra);
        this.usuarioId = usuarioId;
    }

    public static Garantia create(String tenantId, String otOriginalId, String motivo,
                                   BigDecimal costoRepuestos, BigDecimal costoManoObra, String usuarioId) {
        return new Garantia(UUID.randomUUID(), tenantId, otOriginalId, motivo,
                costoRepuestos, costoManoObra, usuarioId);
    }

    public void asignarOtGarantia(String otGarantiaId) {
        this.otGarantiaId = otGarantiaId;
    }

    public UUID getId() { return id; }
    public String getTenantId() { return tenantId; }
    public String getOtOriginalId() { return otOriginalId; }
    public String getOtGarantiaId() { return otGarantiaId; }
    public String getMotivo() { return motivo; }
    public BigDecimal getCostoRepuestos() { return costoRepuestos; }
    public BigDecimal getCostoManoObra() { return costoManoObra; }
    public BigDecimal getCostoTotal() { return costoTotal; }
    public String getUsuarioId() { return usuarioId; }
}
