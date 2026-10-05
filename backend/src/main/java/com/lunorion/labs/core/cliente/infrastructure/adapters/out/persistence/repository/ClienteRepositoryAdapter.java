package com.lunorion.labs.core.cliente.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.cliente.domain.entity.Cliente;
import com.lunorion.labs.core.cliente.domain.filter.ClienteFiltro;
import com.lunorion.labs.core.cliente.domain.ports.out.IClienteRepositoryPort;
import com.lunorion.labs.core.cliente.infrastructure.adapters.out.persistence.entity.ClienteEntity;
import com.lunorion.labs.core.cliente.infrastructure.adapters.out.persistence.mapper.ClienteEntityMapper;
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
public class ClienteRepositoryAdapter implements IClienteRepositoryPort {

    private final ClienteJpaRepository jpaRepository;
    private final ClienteEntityMapper mapper;

    public ClienteRepositoryAdapter(ClienteJpaRepository jpaRepository, ClienteEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Cliente save(Cliente cliente) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(cliente)));
    }

    @Override
    public Optional<Cliente> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public Optional<Cliente> findByNumeroDocumento(String numeroDocumento) {
        return jpaRepository.findByNumeroDocumento(numeroDocumento).map(mapper::toDomain);
    }

    @Override
    public List<Cliente> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Cliente> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public PageResult<Cliente> search(ClienteFiltro filtro) {
        Pageable pageable = PageRequest.of(filtro.safePage(), filtro.safeSize(),
                Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<ClienteEntity> page = jpaRepository.findAll(buildSpecification(filtro), pageable);
        List<Cliente> content = page.getContent().stream().map(mapper::toDomain).collect(Collectors.toList());
        return new PageResult<>(content, page.getNumber(), page.getSize(),
                page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public List<Cliente> searchAll(ClienteFiltro filtro) {
        return jpaRepository.findAll(buildSpecification(filtro), Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    private Specification<ClienteEntity> buildSpecification(ClienteFiltro filtro) {
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
                        cb.like(cb.lower(root.get("razonSocial")), like),
                        cb.like(cb.lower(root.get("numeroDocumento")), like),
                        cb.like(cb.lower(root.get("telefono")), like),
                        cb.like(cb.lower(root.get("email")), like)
                ));
            }

            if ("activo".equalsIgnoreCase(filtro.estado())) {
                predicates.add(cb.isTrue(root.get("activo")));
            } else if ("inactivo".equalsIgnoreCase(filtro.estado())) {
                predicates.add(cb.isFalse(root.get("activo")));
            }

            if (filtro.tipoDocumento() != null && !filtro.tipoDocumento().isBlank()) {
                predicates.add(cb.equal(root.get("tipoDocumento"), filtro.tipoDocumento()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
