package com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.usuario.domain.entity.Permiso;
import com.lunorion.labs.core.usuario.domain.ports.out.IPermisoRepositoryPort;
import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.mapper.PermisoEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class PermisoRepositoryAdapter implements IPermisoRepositoryPort {

    private final PermisoJpaRepository jpaRepository;
    private final PermisoEntityMapper mapper;

    public PermisoRepositoryAdapter(PermisoJpaRepository jpaRepository, PermisoEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Permiso save(Permiso permiso) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(permiso)));
    }

    @Override
    public Optional<Permiso> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public Optional<Permiso> findByCodigo(String codigo) {
        return jpaRepository.findByCodigo(codigo).map(mapper::toDomain);
    }

    @Override
    public List<Permiso> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Permiso> findAllByCodigoIn(List<String> codigos) {
        return jpaRepository.findByCodigoIn(codigos).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }
}
