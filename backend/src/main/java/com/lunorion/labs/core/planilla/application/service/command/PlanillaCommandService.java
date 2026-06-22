package com.lunorion.labs.core.planilla.application.service.command;

import com.lunorion.labs.core.planilla.application.dto.in.RegistrarAsistenciaRequest;
import com.lunorion.labs.core.planilla.application.dto.out.AsistenciaResponse;
import com.lunorion.labs.core.planilla.application.dto.out.BoletaPagoResponse;
import com.lunorion.labs.core.planilla.application.mapper.PlanillaMapper;
import com.lunorion.labs.core.planilla.domain.entity.Asistencia;
import com.lunorion.labs.core.planilla.domain.entity.BoletaPago;
import com.lunorion.labs.core.planilla.domain.ports.in.IPlanillaCommandPort;
import com.lunorion.labs.core.planilla.domain.ports.out.IAsistenciaRepositoryPort;
import com.lunorion.labs.core.planilla.domain.ports.out.IBoletaPagoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class PlanillaCommandService implements IPlanillaCommandPort {

    private final IAsistenciaRepositoryPort asistenciaRepository;
    private final IBoletaPagoRepositoryPort boletaRepository;
    private final PlanillaMapper mapper;

    public PlanillaCommandService(IAsistenciaRepositoryPort asistenciaRepository,
                                   IBoletaPagoRepositoryPort boletaRepository,
                                   PlanillaMapper mapper) {
        this.asistenciaRepository = asistenciaRepository;
        this.boletaRepository = boletaRepository;
        this.mapper = mapper;
    }

    @Override
    public AsistenciaResponse registrarAsistencia(RegistrarAsistenciaRequest request) {
        Asistencia asistencia = mapper.toDomain(request);
        Asistencia saved = asistenciaRepository.save(asistencia);
        return mapper.toResponse(saved);
    }

    @Override
    public BoletaPagoResponse generarBoleta(String tecnicoId, String periodo) {
        BigDecimal sueldoBasico = BigDecimal.valueOf(1025);
        BigDecimal horasExtras = BigDecimal.ZERO;
        BigDecimal comisiones = BigDecimal.ZERO;
        BigDecimal asignacionFamiliar = BigDecimal.valueOf(102.50);

        BoletaPago boleta = mapper.toDomain("TENANT", tecnicoId, periodo, sueldoBasico, horasExtras, comisiones, asignacionFamiliar);
        BoletaPago saved = boletaRepository.save(boleta);
        return mapper.toResponse(saved);
    }
}
