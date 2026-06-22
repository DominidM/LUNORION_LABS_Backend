package com.lunorion.labs.core.auth.domain.entity;

public class TokenAcceso {
    private String token;
    private String tipo;
    private long expiracion;

    public TokenAcceso(String token, long expiracion) {
        this.token = token;
        this.tipo = "Bearer";
        this.expiracion = expiracion;
    }

    public String getToken() { return token; }
    public String getTipo() { return tipo; }
    public long getExpiracion() { return expiracion; }
}
