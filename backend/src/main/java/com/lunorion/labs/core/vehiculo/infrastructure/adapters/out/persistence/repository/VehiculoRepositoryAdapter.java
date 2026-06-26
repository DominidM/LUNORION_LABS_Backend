package com.lunorion.labs.core.vehiculo.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.vehiculo.domain.entity.Vehiculo;
import com.lunorion.labs.core.vehiculo.domain.ports.out.IVehiculoRepositoryPort;
import com.lunorion.labs.core.vehiculo.infrastructure.adapters.out.persistence.entity.VehiculoEntity;
import com.lunorion.labs.core.vehiculo.infrastructure.adapters.out.persistence.mapper.VehiculoEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class VehiculoRepositoryAdapter implements IVehiculoRepositoryPort {

    private final VehiculoJpaRepository jpaRepository;
    private final VehiculoEntityMapper entityMapper;

    public VehiculoRepositoryAdapter(VehiculoJpaRepository jpaRepository, VehiculoEntityMapper entityMapper) {
        this.jpaRepository = jpaRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        VehiculoEntity entity = entityMapper.toEntity(vehiculo);
        VehiculoEntity saved = jpaRepository.save(entity);
        return entityMapper.toDomain(saved);
    }

    @Override
    public Optional<Vehiculo> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(entityMapper::toDomain);
    }

    @Override
    public Optional<Vehiculo> findByPlaca(String placa) {
        return jpaRepository.findByPlaca(placa).map(entityMapper::toDomain);
    }

    @Override
    public List<Vehiculo> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehiculo> findByClienteId(String clienteId) {
        return jpaRepository.findByClienteId(UUID.fromString(clienteId)).stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehiculo> findAll() {
        return jpaRepository.findAll().stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
