package com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.comprobante_electronico.domain.entity.ComprobanteElectronico;
import com.lunorion.labs.core.comprobante_electronico.domain.entity.ResumenDiario;
import com.lunorion.labs.core.comprobante_electronico.domain.ports.out.IComprobanteRepositoryPort;
import com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.persistence.mapper.ComprobanteElectronicoEntityMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ComprobanteRepositoryAdapter implements IComprobanteRepositoryPort {

    private final ComprobanteJpaRepository jpaRepository;
    private final ResumenDiarioJpaRepository resumenDiarioJpaRepository;
    private final ComprobanteElectronicoEntityMapper mapper;

    public ComprobanteRepositoryAdapter(ComprobanteJpaRepository jpaRepository,
                                         ResumenDiarioJpaRepository resumenDiarioJpaRepository,
                                         ComprobanteElectronicoEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.resumenDiarioJpaRepository = resumenDiarioJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ComprobanteElectronico save(ComprobanteElectronico comprobante) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(comprobante)));
    }

    @Override
    public Optional<ComprobanteElectronico> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<ComprobanteElectronico> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ComprobanteElectronico> findByVentaId(String ventaId) {
        return jpaRepository.findByVentaId(ventaId).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ComprobanteElectronico> findByTenantIdAndFechaEmisionBetween(String tenantId, String fechaInicio, String fechaFin) {
        return jpaRepository.findByTenantIdAndFechaEmisionBetween(
                UUID.fromString(tenantId),
                LocalDate.parse(fechaInicio),
                LocalDate.parse(fechaFin)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public ResumenDiario saveResumenDiario(ResumenDiario resumenDiario) {
        return mapper.toResumenDiarioDomain(resumenDiarioJpaRepository.save(mapper.toResumenDiarioEntity(resumenDiario)));
    }

    @Override
    public Optional<ResumenDiario> findResumenDiarioById(String id) {
        return resumenDiarioJpaRepository.findById(UUID.fromString(id)).map(mapper::toResumenDiarioDomain);
    }

    @Override
    public List<ResumenDiario> findAllResumenDiario() {
        return resumenDiarioJpaRepository.findAll().stream()
                .map(mapper::toResumenDiarioDomain).collect(Collectors.toList());
    }
}
