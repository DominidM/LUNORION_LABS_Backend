package com.lunorion.labs.core.tenant.domain.ports.out;

public interface ITenantSunatPort {
    boolean validarRuc(String ruc);
    String consultarRazonSocial(String ruc);
}
