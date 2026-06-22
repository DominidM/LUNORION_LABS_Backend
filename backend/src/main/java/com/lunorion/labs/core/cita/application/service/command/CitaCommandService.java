package com.lunorion.labs.core.cita.application.service.command;

import com.lunorion.labs.core.cita.application.dto.in.CreateCitaRequest;
import com.lunorion.labs.core.cita.application.dto.out.CitaResponse;
import com.lunorion.labs.core.cita.application.mapper.CitaMapper;
import com.lunorion.labs.core.cita.domain.entity.Cita;
import com.lunorion.labs.core.cita.domain.ports.in.ICitaCommandPort;
import com.lunorion.labs.core.cita.domain.ports.out.ICitaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CitaCommandService implements ICitaCommandPort {

    private final ICitaRepositoryPort repository;
    private final CitaMapper mapper;

    public CitaCommandService(ICitaRepositoryPort repository, CitaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CitaResponse crear(CreateCitaRequest request) {
        Cita domain = mapper.toDomain(request);
        Cita saved = repository.save(domain);
        return mapper.toResponse(saved);
    }

    @Override
    public void confirmar(String id) {
        repository.findById(id).ifPresent(c -> {
            c.confirmar();
            repository.save(c);
        });
    }

    @Override
    public void cancelar(String id) {
        repository.findById(id).ifPresent(c -> {
            c.cancelar();
            repository.save(c);
        });
    }
}
