package com.lunorion.labs.core.planilla.domain.entity;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Asistencia {
    private UUID id;
    private String tenantId;
    private String tecnicoId;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private BigDecimal horasTrabajadas;
    private TipoAsistencia tipo;

    public Asistencia() {}

    public Asistencia(UUID id, String tenantId, String tecnicoId, LocalDate fecha,
                      LocalTime horaEntrada, LocalTime horaSalida, TipoAsistencia tipo) {
        this.id = id;
        this.tenantId = tenantId;
        this.tecnicoId = tecnicoId;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.tipo = tipo;
        calcularHorasTrabajadas();
    }

    public static Asistencia registrar(String tenantId, String tecnicoId, LocalDate fecha,
                                        LocalTime horaEntrada, LocalTime horaSalida, TipoAsistencia tipo) {
        return new Asistencia(UUID.randomUUID(), tenantId, tecnicoId, fecha, horaEntrada, horaSalida, tipo);
    }

    private void calcularHorasTrabajadas() {
        if (horaEntrada != null && horaSalida != null) {
            long minutos = Duration.between(horaEntrada, horaSalida).toMinutes();
            this.horasTrabajadas = BigDecimal.valueOf(minutos / 60.0).setScale(2, java.math.RoundingMode.HALF_UP);
        } else {
            this.horasTrabajadas = BigDecimal.ZERO;
        }
    }

    public UUID getId() { return id; }
    public String getTenantId() { return tenantId; }
    public String getTecnicoId() { return tecnicoId; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraEntrada() { return horaEntrada; }
    public LocalTime getHoraSalida() { return horaSalida; }
    public BigDecimal getHorasTrabajadas() { return horasTrabajadas; }
    public TipoAsistencia getTipo() { return tipo; }
}
