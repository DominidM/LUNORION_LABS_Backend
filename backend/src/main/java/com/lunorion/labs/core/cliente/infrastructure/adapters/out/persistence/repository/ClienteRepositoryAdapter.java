package com.lunorion.labs.core.cliente.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.cliente.domain.entity.Cliente;
import com.lunorion.labs.core.cliente.domain.ports.out.IClienteRepositoryPort;
import com.lunorion.labs.core.cliente.infrastructure.adapters.out.persistence.mapper.ClienteEntityMapper;
import org.springframework.stereotype.Repository;

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
}
