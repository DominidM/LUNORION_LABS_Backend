package com.lunorion.labs.core.proveedor.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;
import java.util.UUID;

public class Proveedor extends BaseEntity {
    private String tenantId;
    private String ruc;
    private String razonSocial;
    private String contacto;
    private String telefono;
    private String email;
    private String direccion;
    private String condicionesPago;
    private boolean activo;

    public Proveedor() {}

    public Proveedor(UUID id, String tenantId, String ruc, String razonSocial) {
        super(id);
        this.tenantId = tenantId;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.activo = true;
    }

    public static Proveedor create(String tenantId, String ruc, String razonSocial, String contacto, String telefono, String email, String direccion, String condicionesPago) {
        Proveedor p = new Proveedor(UUID.randomUUID(), tenantId, ruc, razonSocial);
        p.contacto = contacto;
        p.telefono = telefono;
        p.email = email;
        p.direccion = direccion;
        p.condicionesPago = condicionesPago;
        return p;
    }

    public void desactivar() { this.activo = false; markUpdated(); }

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }
    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getCondicionesPago() { return condicionesPago; }
    public void setCondicionesPago(String condicionesPago) { this.condicionesPago = condicionesPago; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
