package com.lunorion.labs.core.planilla.application.dto.out;

import java.math.BigDecimal;
import java.util.List;

public class ProductividadResponse {
    private String tenantId;
    private List<ProductividadTecnico> tecnicos;

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public List<ProductividadTecnico> getTecnicos() { return tecnicos; }
    public void setTecnicos(List<ProductividadTecnico> tecnicos) { this.tecnicos = tecnicos; }

    public static class ProductividadTecnico {
        private String tecnicoId;
        private String nombre;
        private int horasTrabajadas;
        private int ordenesCompletadas;
        private BigDecimal ingresosGenerados;

        public String getTecnicoId() { return tecnicoId; }
        public void setTecnicoId(String tecnicoId) { this.tecnicoId = tecnicoId; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public int getHorasTrabajadas() { return horasTrabajadas; }
        public void setHorasTrabajadas(int horasTrabajadas) { this.horasTrabajadas = horasTrabajadas; }
        public int getOrdenesCompletadas() { return ordenesCompletadas; }
        public void setOrdenesCompletadas(int ordenesCompletadas) { this.ordenesCompletadas = ordenesCompletadas; }
        public BigDecimal getIngresosGenerados() { return ingresosGenerados; }
        public void setIngresosGenerados(BigDecimal ingresosGenerados) { this.ingresosGenerados = ingresosGenerados; }
    }
}
