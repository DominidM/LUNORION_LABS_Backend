package com.lunorion.labs.core.tenant.domain.ports.in;

import com.lunorion.labs.core.tenant.application.dto.in.CreateTenantRequest;
import com.lunorion.labs.core.tenant.application.dto.out.TenantResponse;

public interface ITenantCommandPort {
    TenantResponse create(CreateTenantRequest request);
    void update(String tenantId, CreateTenantRequest request);
    void delete(String tenantId);
}
