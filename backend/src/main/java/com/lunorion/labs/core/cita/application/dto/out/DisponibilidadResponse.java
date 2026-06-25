package com.lunorion.labs.core.cita.application.dto.out;

public class DisponibilidadResponse {
    private String hora;
    private boolean disponible;

    public DisponibilidadResponse() {}

    public DisponibilidadResponse(String hora, boolean disponible) {
        this.hora = hora;
        this.disponible = disponible;
    }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
