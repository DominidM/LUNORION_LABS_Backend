package com.lunorion.labs.core.auth.application.dto.out;

import java.util.List;

public class LoginResponse {
    private String token;
    private String tokenType;
    private long expiresIn;
    private UsuarioInfo usuario;
    private TenantInfo tenant;

    public LoginResponse(String token, long expiresIn, UsuarioInfo usuario, TenantInfo tenant) {
        this.token = token;
        this.tokenType = "Bearer";
        this.expiresIn = expiresIn;
        this.usuario = usuario;
        this.tenant = tenant;
    }

    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }
    public long getExpiresIn() { return expiresIn; }
    public UsuarioInfo getUsuario() { return usuario; }
    public TenantInfo getTenant() { return tenant; }

    public static class UsuarioInfo {
        private String id;
        private String nombres;
        private String apellidos;
        private String email;
        private String rol;
        private List<String> permisos;

        public UsuarioInfo(String id, String nombres, String apellidos, String email, String rol, List<String> permisos) {
            this.id = id;
            this.nombres = nombres;
            this.apellidos = apellidos;
            this.email = email;
            this.rol = rol;
            this.permisos = permisos;
        }

        public String getId() { return id; }
        public String getNombres() { return nombres; }
        public String getApellidos() { return apellidos; }
        public String getEmail() { return email; }
        public String getRol() { return rol; }
        public List<String> getPermisos() { return permisos; }
    }

    public static class TenantInfo {
        private String id;
        private String ruc;
        private String razonSocial;
        private String nombreComercial;
        private String logoUrl;
        private String colorPrimario;
        private String colorSecundario;

        public TenantInfo(String id, String ruc, String razonSocial, String nombreComercial,
                          String logoUrl, String colorPrimario, String colorSecundario) {
            this.id = id;
            this.ruc = ruc;
            this.razonSocial = razonSocial;
            this.nombreComercial = nombreComercial;
            this.logoUrl = logoUrl;
            this.colorPrimario = colorPrimario;
            this.colorSecundario = colorSecundario;
        }

        public String getId() { return id; }
        public String getRuc() { return ruc; }
        public String getRazonSocial() { return razonSocial; }
        public String getNombreComercial() { return nombreComercial; }
        public String getLogoUrl() { return logoUrl; }
        public String getColorPrimario() { return colorPrimario; }
        public String getColorSecundario() { return colorSecundario; }
    }
}
