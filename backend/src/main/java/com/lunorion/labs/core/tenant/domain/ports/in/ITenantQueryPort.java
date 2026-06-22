package com.lunorion.labs.core.tenant.domain.ports.in;

import com.lunorion.labs.core.tenant.application.dto.out.TenantResponse;

import java.util.List;
import java.util.Optional;

public interface ITenantQueryPort {
    Optional<TenantResponse> findById(String id);
    Optional<TenantResponse> findByRuc(String ruc);
    List<TenantResponse> findAll();
}
