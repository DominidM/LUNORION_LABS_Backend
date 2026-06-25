package com.lunorion.labs.core.orden_trabajo.application.dto.out;

import java.util.List;

public class KanbanResponse {
    private String estado;
    private List<OrdenTrabajoResumen> ordenes;

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public List<OrdenTrabajoResumen> getOrdenes() { return ordenes; }
    public void setOrdenes(List<OrdenTrabajoResumen> ordenes) { this.ordenes = ordenes; }

    public static class OrdenTrabajoResumen {
        private String id;
        private String numeroOt;
        private String clienteId;
        private String vehiculoId;
        private String tecnicoId;
        private String motivoIngreso;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getNumeroOt() { return numeroOt; }
        public void setNumeroOt(String numeroOt) { this.numeroOt = numeroOt; }
        public String getClienteId() { return clienteId; }
        public void setClienteId(String clienteId) { this.clienteId = clienteId; }
        public String getVehiculoId() { return vehiculoId; }
        public void setVehiculoId(String vehiculoId) { this.vehiculoId = vehiculoId; }
        public String getTecnicoId() { return tecnicoId; }
        public void setTecnicoId(String tecnicoId) { this.tecnicoId = tecnicoId; }
        public String getMotivoIngreso() { return motivoIngreso; }
        public void setMotivoIngreso(String motivoIngreso) { this.motivoIngreso = motivoIngreso; }
    }
}
