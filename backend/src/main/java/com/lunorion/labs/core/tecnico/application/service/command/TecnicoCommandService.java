package com.lunorion.labs.core.tecnico.application.service.command;

import com.lunorion.labs.core.tecnico.application.dto.in.CreateTecnicoRequest;
import com.lunorion.labs.core.tecnico.application.dto.out.TecnicoResponse;
import com.lunorion.labs.core.tecnico.application.mapper.TecnicoMapper;
import com.lunorion.labs.core.tecnico.domain.entity.Tecnico;
import com.lunorion.labs.core.tecnico.domain.ports.in.ITecnicoCommandPort;
import com.lunorion.labs.core.tecnico.domain.ports.out.ITecnicoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class TecnicoCommandService implements ITecnicoCommandPort {

    private final ITecnicoRepositoryPort repository;
    private final TecnicoMapper mapper;

    public TecnicoCommandService(ITecnicoRepositoryPort repository, TecnicoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TecnicoResponse create(CreateTecnicoRequest request) {
        Tecnico tecnico = mapper.toDomain(request);
        Tecnico saved = repository.save(tecnico);
        return mapper.toResponse(saved);
    }

    @Override
    public void desactivar(String id) {
        repository.findById(id).ifPresent(tecnico -> {
            tecnico.desactivar();
            repository.save(tecnico);
        });
    }

    @Override
    public void actualizarTarifa(String id, BigDecimal tarifa) {
        repository.findById(id).ifPresent(tecnico -> {
            tecnico.actualizarTarifa(tarifa);
            repository.save(tecnico);
        });
    }
}
