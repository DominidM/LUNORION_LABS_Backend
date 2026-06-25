package com.lunorion.labs.core.tenant.domain.ports.out;

import com.lunorion.labs.core.tenant.domain.entity.Tenant;

import java.util.List;
import java.util.Optional;

public interface ITenantRepositoryPort {
    Tenant save(Tenant tenant);
    Optional<Tenant> findById(String id);
    Optional<Tenant> findByRuc(String ruc);
    List<Tenant> findAll();
    void deleteById(String id);
}
