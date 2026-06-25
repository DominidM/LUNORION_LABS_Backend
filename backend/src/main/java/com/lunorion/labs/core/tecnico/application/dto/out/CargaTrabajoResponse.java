package com.lunorion.labs.core.tecnico.application.dto.out;

import java.math.BigDecimal;

public class CargaTrabajoResponse {
    private String tecnicoId;
    private String nombre;
    private int ordenesAbiertas;
    private BigDecimal horasPendientes;

    public String getTecnicoId() { return tecnicoId; }
    public void setTecnicoId(String tecnicoId) { this.tecnicoId = tecnicoId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getOrdenesAbiertas() { return ordenesAbiertas; }
    public void setOrdenesAbiertas(int ordenesAbiertas) { this.ordenesAbiertas = ordenesAbiertas; }
    public BigDecimal getHorasPendientes() { return horasPendientes; }
    public void setHorasPendientes(BigDecimal horasPendientes) { this.horasPendientes = horasPendientes; }
}
