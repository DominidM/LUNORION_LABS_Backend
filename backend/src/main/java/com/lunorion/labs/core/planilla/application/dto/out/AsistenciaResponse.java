package com.lunorion.labs.core.planilla.application.dto.out;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaResponse {
    private String id;
    private String tenantId;
    private String tecnicoId;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private BigDecimal horasTrabajadas;
    private String tipo;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getTecnicoId() { return tecnicoId; }
    public void setTecnicoId(String tecnicoId) { this.tecnicoId = tecnicoId; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(LocalTime horaEntrada) { this.horaEntrada = horaEntrada; }
    public LocalTime getHoraSalida() { return horaSalida; }
    public void setHoraSalida(LocalTime horaSalida) { this.horaSalida = horaSalida; }
    public BigDecimal getHorasTrabajadas() { return horasTrabajadas; }
    public void setHorasTrabajadas(BigDecimal horasTrabajadas) { this.horasTrabajadas = horasTrabajadas; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
