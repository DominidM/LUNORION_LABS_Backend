package com.lunorion.labs.core.usuario.domain.ports.out;

import com.lunorion.labs.core.usuario.domain.entity.Permiso;

import java.util.List;
import java.util.Optional;

public interface IPermisoRepositoryPort {
    Permiso save(Permiso permiso);
    Optional<Permiso> findById(String id);
    Optional<Permiso> findByCodigo(String codigo);
    List<Permiso> findAll();
    List<Permiso> findAllByCodigoIn(List<String> codigos);
}
