package com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.orden_trabajo.domain.entity.OrdenTrabajo;
import com.lunorion.labs.core.orden_trabajo.domain.entity.OtInsumo;
import com.lunorion.labs.core.orden_trabajo.domain.entity.OtManoObra;
import com.lunorion.labs.core.orden_trabajo.domain.filter.OrdenTrabajoFiltro;
import com.lunorion.labs.core.orden_trabajo.domain.ports.out.IOrdenTrabajoRepositoryPort;
import com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.entity.OrdenTrabajoEntity;
import com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.mapper.OrdenTrabajoEntityMapper;
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
public class OrdenTrabajoRepositoryAdapter implements IOrdenTrabajoRepositoryPort {

    private final OrdenTrabajoJpaRepository jpaRepository;
    private final OtInsumoJpaRepository insumoJpaRepository;
    private final OtManoObraJpaRepository manoObraJpaRepository;
    private final OrdenTrabajoEntityMapper mapper;

    public OrdenTrabajoRepositoryAdapter(OrdenTrabajoJpaRepository jpaRepository,
                                          OtInsumoJpaRepository insumoJpaRepository,
                                          OtManoObraJpaRepository manoObraJpaRepository,
                                          OrdenTrabajoEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.insumoJpaRepository = insumoJpaRepository;
        this.manoObraJpaRepository = manoObraJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public OrdenTrabajo save(OrdenTrabajo ordenTrabajo) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(ordenTrabajo)));
    }

    @Override
    public void saveInsumo(OtInsumo insumo) {
        insumoJpaRepository.save(mapper.toInsumoEntity(insumo));
    }

    @Override
    public void saveAllInsumos(List<OtInsumo> insumos) {
        insumos.forEach(this::saveInsumo);
    }

    @Override
    public void saveManoObra(OtManoObra manoObra) {
        manoObraJpaRepository.save(mapper.toManoObraEntity(manoObra));
    }

    @Override
    public void saveAllManoObra(List<OtManoObra> manosObra) {
        manosObra.forEach(this::saveManoObra);
    }

    @Override
    public Optional<OrdenTrabajo> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<OrdenTrabajo> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrdenTrabajo> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public PageResult<OrdenTrabajo> search(OrdenTrabajoFiltro filtro) {
        Pageable pageable = PageRequest.of(filtro.safePage(), filtro.safeSize(),
                Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<OrdenTrabajoEntity> page = jpaRepository.findAll(buildSpecification(filtro), pageable);
        List<OrdenTrabajo> content = page.getContent().stream().map(mapper::toDomain).collect(Collectors.toList());
        return new PageResult<>(content, page.getNumber(), page.getSize(),
                page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public List<OrdenTrabajo> searchAll(OrdenTrabajoFiltro filtro) {
        return jpaRepository.findAll(buildSpecification(filtro), Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    private Specification<OrdenTrabajoEntity> buildSpecification(OrdenTrabajoFiltro filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.tenantId() != null && !filtro.tenantId().isBlank()) {
                predicates.add(cb.equal(root.get("tenantId"), UUID.fromString(filtro.tenantId())));
            }

            if (filtro.search() != null && !filtro.search().isBlank()) {
                String like = "%" + filtro.search().trim().toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("numeroOt")), like),
                        cb.like(cb.lower(root.get("motivoIngreso")), like)
                ));
            }

            if (filtro.estado() != null && !filtro.estado().isBlank()) {
                predicates.add(cb.equal(cb.upper(root.get("estado")), filtro.estado().trim().toUpperCase()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<OrdenTrabajo> findByEstado(String estado, String tenantId) {
        return jpaRepository.findByEstadoAndTenantId(estado, UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteInsumoById(String insumoId) {
        insumoJpaRepository.deleteById(UUID.fromString(insumoId));
    }

    @Override
    public void deleteManoObraById(String laborId) {
        manoObraJpaRepository.deleteById(UUID.fromString(laborId));
    }

    @Override
    public Optional<OtInsumo> findInsumoById(String id) {
        return insumoJpaRepository.findById(UUID.fromString(id)).map(mapper::toInsumoDomain);
    }

    @Override
    public List<OtInsumo> findInsumosByOrdenTrabajoId(String ordenTrabajoId) {
        return insumoJpaRepository.findByOrdenTrabajoId(UUID.fromString(ordenTrabajoId)).stream()
                .map(mapper::toInsumoDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<OtManoObra> findManoObraById(String id) {
        return manoObraJpaRepository.findById(UUID.fromString(id)).map(mapper::toManoObraDomain);
    }

    @Override
    public List<OtManoObra> findManosObraByOrdenTrabajoId(String ordenTrabajoId) {
        return manoObraJpaRepository.findByOrdenTrabajoId(UUID.fromString(ordenTrabajoId)).stream()
                .map(mapper::toManoObraDomain).collect(Collectors.toList());
    }
}
