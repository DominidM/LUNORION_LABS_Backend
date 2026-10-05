package com.lunorion.labs.core.orden_trabajo.domain.filter;

import com.lunorion.labs.shared.domain.PageSizes;

public record OrdenTrabajoFiltro(
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
