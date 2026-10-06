package com.lunorion.labs.core.proveedor.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.proveedor.domain.entity.Proveedor;
import com.lunorion.labs.core.proveedor.domain.filter.ProveedorFiltro;
import com.lunorion.labs.core.proveedor.domain.ports.out.IProveedorRepositoryPort;
import com.lunorion.labs.core.proveedor.infrastructure.adapters.out.persistence.entity.ProveedorEntity;
import com.lunorion.labs.core.proveedor.infrastructure.adapters.out.persistence.mapper.ProveedorEntityMapper;
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
public class ProveedorRepositoryAdapter implements IProveedorRepositoryPort {

    private final ProveedorJpaRepository jpaRepository;
    private final ProveedorEntityMapper mapper;

    public ProveedorRepositoryAdapter(ProveedorJpaRepository jpaRepository, ProveedorEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(proveedor)));
    }

    @Override
    public Optional<Proveedor> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<Proveedor> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Proveedor> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public PageResult<Proveedor> search(ProveedorFiltro filtro) {
        Pageable pageable = PageRequest.of(filtro.safePage(), filtro.safeSize(),
                Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<ProveedorEntity> page = jpaRepository.findAll(buildSpecification(filtro), pageable);
        List<Proveedor> content = page.getContent().stream().map(mapper::toDomain).collect(Collectors.toList());
        return new PageResult<>(content, page.getNumber(), page.getSize(),
                page.getTotalElements(), page.getTotalPages());
    }

    private Specification<ProveedorEntity> buildSpecification(ProveedorFiltro filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.tenantId() != null && !filtro.tenantId().isBlank()) {
                predicates.add(cb.equal(root.get("tenantId"), UUID.fromString(filtro.tenantId())));
            }

            if (filtro.search() != null && !filtro.search().isBlank()) {
                String like = "%" + filtro.search().trim().toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("razonSocial")), like),
                        cb.like(cb.lower(root.get("ruc")), like),
                        cb.like(cb.lower(root.get("contacto")), like),
                        cb.like(cb.lower(root.get("telefono")), like),
                        cb.like(cb.lower(root.get("email")), like),
                        cb.like(cb.lower(root.get("direccion")), like)
                ));
            }

            if ("activo".equalsIgnoreCase(filtro.estado())) {
                predicates.add(cb.isTrue(root.get("activo")));
            } else if ("inactivo".equalsIgnoreCase(filtro.estado())) {
                predicates.add(cb.isFalse(root.get("activo")));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
