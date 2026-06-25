package com.lunorion.labs.core.auth.domain.ports.out;

import java.util.List;

public class AuthUser {
    private String id;
    private String email;
    private String passwordHash;
    private String tenantId;
    private String rol;
    private boolean activo;
    private String nombres;
    private String apellidos;
    private List<String> permisos;

    public AuthUser(String id, String email, String passwordHash, String tenantId, String rol,
                    boolean activo, String nombres, String apellidos, List<String> permisos) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.tenantId = tenantId;
        this.rol = rol;
        this.activo = activo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.permisos = permisos;
    }

    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getTenantId() { return tenantId; }
    public String getRol() { return rol; }
    public boolean isActivo() { return activo; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public List<String> getPermisos() { return permisos; }
}
