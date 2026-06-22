package com.lunorion.labs.core.usuario.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;
import java.util.UUID;

public class Usuario extends BaseEntity {
    private String tenantId;
    private String email;
    private String passwordHash;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String rol;
    private boolean activo;

    public Usuario() {}

    public Usuario(UUID id, String tenantId, String email, String nombres, String apellidos, String rol) {
        super(id);
        this.tenantId = tenantId;
        this.email = email;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rol = rol;
        this.activo = true;
    }

    public static Usuario create(String tenantId, String email, String nombres, String apellidos, String dni, String telefono, String rol) {
        return new Usuario(UUID.randomUUID(), tenantId, email, nombres, apellidos, rol);
    }

    public void desactivar() { this.activo = false; markUpdated(); }
    public void activar() { this.activo = true; markUpdated(); }

    public String getTenantId() { return tenantId; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getDni() { return dni; }
    public String getTelefono() { return telefono; }
    public String getRol() { return rol; }
    public boolean isActivo() { return activo; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
