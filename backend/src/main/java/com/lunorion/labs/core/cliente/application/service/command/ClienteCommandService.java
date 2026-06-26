package com.lunorion.labs.core.cliente.application.service.command;

import com.lunorion.labs.core.cliente.application.dto.in.CreateClienteRequest;
import com.lunorion.labs.core.cliente.application.dto.out.ClienteResponse;
import com.lunorion.labs.core.cliente.application.mapper.ClienteMapper;
import com.lunorion.labs.core.cliente.domain.entity.Cliente;
import com.lunorion.labs.core.cliente.domain.ports.in.IClienteCommandPort;
import com.lunorion.labs.core.cliente.domain.ports.out.IClienteRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteCommandService implements IClienteCommandPort {

    private final IClienteRepositoryPort repository;
    private final ClienteMapper mapper;

    public ClienteCommandService(IClienteRepositoryPort repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ClienteResponse create(CreateClienteRequest request) {
        if (request.getNumeroDocumento() != null &&
                repository.findByNumeroDocumento(request.getNumeroDocumento()).isPresent()) {
            throw new IllegalArgumentException(
                    "El número de documento " + request.getNumeroDocumento() + " ya está registrado");
        }
        Cliente cliente = mapper.toDomain(request);
        Cliente saved = repository.save(cliente);
        return mapper.toResponse(saved);
    }

    @Override
    public ClienteResponse update(String id, CreateClienteRequest request) {
        if (request.getNumeroDocumento() != null) {
            repository.findByNumeroDocumento(request.getNumeroDocumento()).ifPresent(existing -> {
                if (!existing.getId().toString().equals(id)) {
                    throw new IllegalArgumentException(
                            "El número de documento " + request.getNumeroDocumento() + " ya está registrado por otro cliente");
                }
            });
        }
        return repository.findById(id).map(cliente -> {
            mapper.updateDomain(cliente, request);
            Cliente saved = repository.save(cliente);
            return mapper.toResponse(saved);
        }).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + id));
    }

    @Override
    public void desactivar(String id) {
        repository.findById(id).ifPresent(cliente -> {
            cliente.desactivar();
            repository.save(cliente);
        });
    }

    @Override
    public void activar(String id) {
        repository.findById(id).ifPresent(cliente -> {
            cliente.activar();
            repository.save(cliente);
        });
    }
}
