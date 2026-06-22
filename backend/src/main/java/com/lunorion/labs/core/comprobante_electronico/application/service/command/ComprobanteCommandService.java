package com.lunorion.labs.core.comprobante_electronico.application.service.command;

import com.lunorion.labs.core.comprobante_electronico.application.dto.in.CreateComprobanteRequest;
import com.lunorion.labs.core.comprobante_electronico.application.dto.out.ComprobanteResponse;
import com.lunorion.labs.core.comprobante_electronico.application.mapper.ComprobanteMapper;
import com.lunorion.labs.core.comprobante_electronico.domain.entity.ComprobanteElectronico;
import com.lunorion.labs.core.comprobante_electronico.domain.ports.in.IComprobanteCommandPort;
import com.lunorion.labs.core.comprobante_electronico.domain.ports.out.IComprobanteRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ComprobanteCommandService implements IComprobanteCommandPort {

    private final IComprobanteRepositoryPort repository;
    private final ComprobanteMapper mapper;

    public ComprobanteCommandService(IComprobanteRepositoryPort repository, ComprobanteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ComprobanteResponse create(CreateComprobanteRequest request) {
        ComprobanteElectronico domain = mapper.toDomain(request);
        ComprobanteElectronico saved = repository.save(domain);
        return mapper.toResponse(saved);
    }

    @Override
    public void firmar(String id) {
        repository.findById(id).ifPresent(c -> {
            c.firmar();
            repository.save(c);
        });
    }

    @Override
    public void enviarSunat(String id) {
        repository.findById(id).ifPresent(c -> {
            c.enviarSunat();
            repository.save(c);
        });
    }

    @Override
    public void aceptar(String id) {
        repository.findById(id).ifPresent(c -> {
            c.aceptar();
            repository.save(c);
        });
    }

    @Override
    public void rechazar(String id, String error) {
        repository.findById(id).ifPresent(c -> {
            c.rechazar(error);
            repository.save(c);
        });
    }
}
