package com.lunorion.labs.core.usuario.domain.ports.in;

import com.lunorion.labs.core.usuario.application.dto.out.PermisoResponse;
import com.lunorion.labs.core.usuario.application.dto.out.UsuarioResponse;
import com.lunorion.labs.core.usuario.domain.filter.UsuarioFiltro;
import com.lunorion.labs.shared.application.dto.out.PagedResponse;

import java.util.List;
import java.util.Optional;

public interface IUsuarioQueryPort {
    Optional<UsuarioResponse> findById(String id);
    Optional<UsuarioResponse> findByEmail(String email);
    List<UsuarioResponse> findByTenantId(String tenantId);
    PagedResponse<UsuarioResponse> search(UsuarioFiltro filtro);
    List<UsuarioResponse> searchAll(UsuarioFiltro filtro);
    List<PermisoResponse> listarPermisos(String tenantId);
}
