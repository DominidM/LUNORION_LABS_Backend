package com.lunorion.labs.core.cotizacion.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.cotizacion.domain.entity.Cotizacion;
import com.lunorion.labs.core.cotizacion.domain.ports.out.ICotizacionRepositoryPort;
import com.lunorion.labs.core.cotizacion.infrastructure.adapters.out.persistence.mapper.CotizacionEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CotizacionRepositoryAdapter implements ICotizacionRepositoryPort {

    private final CotizacionJpaRepository jpaRepository;
    private final CotizacionEntityMapper mapper;

    public CotizacionRepositoryAdapter(CotizacionJpaRepository jpaRepository, CotizacionEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Cotizacion save(Cotizacion cotizacion) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(cotizacion)));
    }

    @Override
    public Optional<Cotizacion> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<Cotizacion> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Cotizacion> findByClienteId(String clienteId) {
        return jpaRepository.findByClienteId(UUID.fromString(clienteId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Cotizacion> findByEstado(String estado) {
        return jpaRepository.findByEstado(estado).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Cotizacion> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }
}
