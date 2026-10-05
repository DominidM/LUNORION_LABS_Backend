package com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.usuario.domain.entity.Usuario;
import com.lunorion.labs.core.usuario.domain.filter.UsuarioFiltro;
import com.lunorion.labs.core.usuario.domain.ports.out.IUsuarioRepositoryPort;
import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.mapper.UsuarioEntityMapper;
import com.lunorion.labs.shared.domain.PageResult;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepositoryAdapter implements IUsuarioRepositoryPort {

    private final UsuarioJpaRepository jpaRepository;
    private final UsuarioEntityMapper mapper;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository jpaRepository, UsuarioEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(usuario)));
    }

    @Override
    public Optional<Usuario> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public List<Usuario> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public PageResult<Usuario> search(UsuarioFiltro filtro) {
        Pageable pageable = PageRequest.of(filtro.safePage(), filtro.safeSize(),
                Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.entity.UsuarioEntity> page =
                jpaRepository.findAll(buildSpecification(filtro), pageable);
        List<Usuario> content = page.getContent().stream().map(mapper::toDomain).collect(Collectors.toList());
        return new PageResult<>(content, page.getNumber(), page.getSize(),
                page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public List<Usuario> searchAll(UsuarioFiltro filtro) {
        return jpaRepository.findAll(buildSpecification(filtro), Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    private Specification<com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.entity.UsuarioEntity>
    buildSpecification(UsuarioFiltro filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.tenantId() != null && !filtro.tenantId().isBlank()) {
                predicates.add(cb.equal(root.get("tenantId"), UUID.fromString(filtro.tenantId())));
            }

            if (filtro.search() != null && !filtro.search().isBlank()) {
                String like = "%" + filtro.search().trim().toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("nombres")), like),
                        cb.like(cb.lower(root.get("apellidos")), like),
                        cb.like(cb.lower(root.get("dni")), like),
                        cb.like(cb.lower(root.get("email")), like),
                        cb.like(cb.lower(root.get("telefono")), like)
                ));
            }

            if ("activo".equalsIgnoreCase(filtro.estado())) {
                predicates.add(cb.isTrue(root.get("activo")));
            } else if ("inactivo".equalsIgnoreCase(filtro.estado())) {
                predicates.add(cb.isFalse(root.get("activo")));
            }

            if (filtro.rol() != null && !filtro.rol().isBlank()) {
                predicates.add(cb.equal(root.get("rol"), filtro.rol()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
