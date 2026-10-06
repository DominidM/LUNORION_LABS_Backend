package com.lunorion.labs.core.proveedor.domain.filter;

import com.lunorion.labs.shared.domain.PageSizes;

public record ProveedorFiltro(
        String tenantId,
        String search,
        String estado,
        int page,
        int size
) {

    public int safePage() {
        return Math.max(page, 0);
    }

    public int safeSize() {
        return PageSizes.normalize(size);
    }
}
