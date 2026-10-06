package com.lunorion.labs.core.cliente.domain.filter;

import com.lunorion.labs.shared.domain.PageSizes;

public record ClienteFiltro(
        String tenantId,
        String search,
        String estado,
        String tipoDocumento,
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
