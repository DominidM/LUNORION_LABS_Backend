package com.lunorion.labs.core.orden_trabajo.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class OtManoObra extends BaseEntity {
    private String ordenTrabajoId;
    private String tecnicoId;
    private String servicioDescripcion;
    private BigDecimal horas;
    private BigDecimal tarifaHora;
    private BigDecimal subtotal;

    public OtManoObra() {}

    public OtManoObra(UUID id, String ordenTrabajoId, String tecnicoId, String servicioDescripcion,
                      BigDecimal horas, BigDecimal tarifaHora, BigDecimal subtotal) {
        super(id);
        this.ordenTrabajoId = ordenTrabajoId;
        this.tecnicoId = tecnicoId;
        this.servicioDescripcion = servicioDescripcion;
        this.horas = horas;
        this.tarifaHora = tarifaHora;
        this.subtotal = subtotal;
    }

    public static OtManoObra create(String ordenTrabajoId, String tecnicoId, String servicioDescripcion,
                                     BigDecimal horas, BigDecimal tarifaHora, BigDecimal subtotal) {
        return new OtManoObra(UUID.randomUUID(), ordenTrabajoId, tecnicoId, servicioDescripcion, horas, tarifaHora, subtotal);
    }

    public String getOrdenTrabajoId() { return ordenTrabajoId; }
    public String getTecnicoId() { return tecnicoId; }
    public String getServicioDescripcion() { return servicioDescripcion; }
    public BigDecimal getHoras() { return horas; }
    public BigDecimal getTarifaHora() { return tarifaHora; }
    public BigDecimal getSubtotal() { return subtotal; }
}
