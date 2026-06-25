package com.lunorion.labs.core.tecnico.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.tecnico.domain.entity.ConfiguracionComision;
import com.lunorion.labs.core.tecnico.domain.entity.Tecnico;
import com.lunorion.labs.core.tecnico.domain.ports.out.ITecnicoRepositoryPort;
import com.lunorion.labs.core.tecnico.infrastructure.adapters.out.persistence.mapper.TecnicoEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TecnicoRepositoryAdapter implements ITecnicoRepositoryPort {

    private final TecnicoJpaRepository jpaRepository;
    private final ConfiguracionComisionJpaRepository comisionJpaRepository;
    private final TecnicoEntityMapper mapper;

    public TecnicoRepositoryAdapter(TecnicoJpaRepository jpaRepository,
                                     ConfiguracionComisionJpaRepository comisionJpaRepository,
                                     TecnicoEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.comisionJpaRepository = comisionJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Tecnico save(Tecnico tecnico) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(tecnico)));
    }

    @Override
    public Optional<Tecnico> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<Tecnico> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Tecnico> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public ConfiguracionComision saveComision(ConfiguracionComision comision) {
        return mapper.toComisionDomain(comisionJpaRepository.save(mapper.toComisionEntity(comision)));
    }

    @Override
    public Optional<ConfiguracionComision> findComisionById(String id) {
        return comisionJpaRepository.findById(UUID.fromString(id)).map(mapper::toComisionDomain);
    }

    @Override
    public List<ConfiguracionComision> findComisionesByTecnicoId(String tecnicoId) {
        return comisionJpaRepository.findByTecnicoId(UUID.fromString(tecnicoId)).stream()
                .map(mapper::toComisionDomain).collect(Collectors.toList());
    }
}
