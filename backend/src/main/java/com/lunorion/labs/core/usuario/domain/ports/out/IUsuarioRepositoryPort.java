package com.lunorion.labs.core.usuario.domain.ports.out;

import com.lunorion.labs.core.usuario.domain.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepositoryPort {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(String id);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByTenantId(String tenantId);
    void deleteById(String id);
}
