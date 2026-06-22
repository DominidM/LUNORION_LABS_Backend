package com.lunorion.labs.core.orden_trabajo.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class OrdenTrabajo extends BaseEntity {
    private String tenantId;
    private String clienteId;
    private String vehiculoId;
    private String tecnicoId;
    private String numeroOt;
    private String estado;
    private String motivoIngreso;
    private Integer kilometrajeIngreso;
    private LocalDate fechaPrometida;
    private BigDecimal totalRepuestos;
    private BigDecimal totalManoObra;
    private BigDecimal total;
    private String otOrigenId;
    private String usuarioCreoId;
    private String usuarioCerroId;

    public OrdenTrabajo() {}

    public OrdenTrabajo(UUID id, String tenantId, String clienteId, String vehiculoId, String tecnicoId,
                        String numeroOt, String estado, String motivoIngreso, String usuarioCreoId) {
        super(id);
        this.tenantId = tenantId;
        this.clienteId = clienteId;
        this.vehiculoId = vehiculoId;
        this.tecnicoId = tecnicoId;
        this.numeroOt = numeroOt;
        this.estado = estado;
        this.motivoIngreso = motivoIngreso;
        this.usuarioCreoId = usuarioCreoId;
        this.totalRepuestos = BigDecimal.ZERO;
        this.totalManoObra = BigDecimal.ZERO;
        this.total = BigDecimal.ZERO;
    }

    public static OrdenTrabajo create(String tenantId, String clienteId, String vehiculoId, String tecnicoId,
                                       String numeroOt, String estado, String motivoIngreso, String usuarioCreoId) {
        return new OrdenTrabajo(UUID.randomUUID(), tenantId, clienteId, vehiculoId, tecnicoId,
                numeroOt, estado, motivoIngreso, usuarioCreoId);
    }

    public String getTenantId() { return tenantId; }
    public String getClienteId() { return clienteId; }
    public String getVehiculoId() { return vehiculoId; }
    public String getTecnicoId() { return tecnicoId; }
    public String getNumeroOt() { return numeroOt; }
    public String getEstado() { return estado; }
    public String getMotivoIngreso() { return motivoIngreso; }
    public Integer getKilometrajeIngreso() { return kilometrajeIngreso; }
    public LocalDate getFechaPrometida() { return fechaPrometida; }
    public BigDecimal getTotalRepuestos() { return totalRepuestos; }
    public BigDecimal getTotalManoObra() { return totalManoObra; }
    public BigDecimal getTotal() { return total; }
    public String getOtOrigenId() { return otOrigenId; }
    public String getUsuarioCreoId() { return usuarioCreoId; }
    public String getUsuarioCerroId() { return usuarioCerroId; }
}
