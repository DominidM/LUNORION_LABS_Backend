package com.lunorion.labs.core.cliente.domain.ports.out;

import com.lunorion.labs.core.cliente.domain.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteRepositoryPort {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(String id);
    Optional<Cliente> findByNumeroDocumento(String numeroDocumento);
    List<Cliente> findByTenantId(String tenantId);
    List<Cliente> findAll();
}
