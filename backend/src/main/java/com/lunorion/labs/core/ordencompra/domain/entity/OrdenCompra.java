package com.lunorion.labs.core.ordencompra.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class OrdenCompra extends BaseEntity {

    private String tenantId;
    private String proveedorId;
    private String numeroOrden;
    private LocalDate fechaEmision;
    private String estado;
    private BigDecimal total;
    private String observacion;
    private String usuarioId;

    public OrdenCompra() {}

    public OrdenCompra(UUID id, String tenantId, String proveedorId, String numeroOrden,
                       LocalDate fechaEmision, String estado, BigDecimal total,
                       String observacion, String usuarioId) {
        super(id);
        this.tenantId = tenantId;
        this.proveedorId = proveedorId;
        this.numeroOrden = numeroOrden;
        this.fechaEmision = fechaEmision;
        this.estado = estado;
        this.total = total;
        this.observacion = observacion;
        this.usuarioId = usuarioId;
    }

    public static OrdenCompra create(String tenantId, String proveedorId, String numeroOrden,
                                     LocalDate fechaEmision, String estado, BigDecimal total,
                                     String observacion, String usuarioId) {
        return new OrdenCompra(UUID.randomUUID(), tenantId, proveedorId, numeroOrden,
                fechaEmision, estado, total, observacion, usuarioId);
    }

    public void aprobar() {
        this.estado = "APROBADA";
        markUpdated();
    }

    public void completar() {
        this.estado = "COMPLETADA";
        markUpdated();
    }

    public void anular() {
        this.estado = "ANULADA";
        markUpdated();
    }

    public String getTenantId() { return tenantId; }
    public String getProveedorId() { return proveedorId; }
    public String getNumeroOrden() { return numeroOrden; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public String getEstado() { return estado; }
    public BigDecimal getTotal() { return total; }
    public String getObservacion() { return observacion; }
    public String getUsuarioId() { return usuarioId; }
    public void setObservacion(String observacion) { this.observacion = observacion; markUpdated(); }
    public void setTotal(BigDecimal total) { this.total = total; markUpdated(); }
}
