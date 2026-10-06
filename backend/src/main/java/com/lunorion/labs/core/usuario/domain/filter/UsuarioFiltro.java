package com.lunorion.labs.core.usuario.domain.filter;

import com.lunorion.labs.shared.domain.PageSizes;

public record UsuarioFiltro(
        String tenantId,
        String search,
        String estado,
        String rol,
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
