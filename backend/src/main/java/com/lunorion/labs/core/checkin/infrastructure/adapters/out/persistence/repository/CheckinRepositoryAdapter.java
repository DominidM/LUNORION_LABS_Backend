package com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.checkin.domain.entity.Checkin;
import com.lunorion.labs.core.checkin.domain.entity.CheckinFoto;
import com.lunorion.labs.core.checkin.domain.entity.Garantia;
import com.lunorion.labs.core.checkin.domain.ports.out.ICheckinRepositoryPort;
import com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.mapper.CheckinEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CheckinRepositoryAdapter implements ICheckinRepositoryPort {

    private final CheckinJpaRepository checkinJpaRepository;
    private final CheckinFotoJpaRepository fotoJpaRepository;
    private final GarantiaJpaRepository garantiaJpaRepository;
    private final CheckinEntityMapper mapper;

    public CheckinRepositoryAdapter(CheckinJpaRepository checkinJpaRepository,
                                     CheckinFotoJpaRepository fotoJpaRepository,
                                     GarantiaJpaRepository garantiaJpaRepository,
                                     CheckinEntityMapper mapper) {
        this.checkinJpaRepository = checkinJpaRepository;
        this.fotoJpaRepository = fotoJpaRepository;
        this.garantiaJpaRepository = garantiaJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Checkin save(Checkin checkin) {
        return mapper.toDomain(checkinJpaRepository.save(mapper.toEntity(checkin)));
    }

    @Override
    public Optional<Checkin> findById(String id) {
        return checkinJpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<Checkin> findByTenantId(String tenantId) {
        return checkinJpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Checkin> findByOtId(String otId) {
        return checkinJpaRepository.findByOtId(UUID.fromString(otId)).map(mapper::toDomain);
    }

    @Override
    public CheckinFoto saveFoto(CheckinFoto foto) {
        return mapper.toFotoDomain(fotoJpaRepository.save(mapper.toFotoEntity(foto)));
    }

    @Override
    public void deleteById(String id) {
        checkinJpaRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<CheckinFoto> findFotosByCheckinId(String checkinId) {
        return fotoJpaRepository.findByCheckinId(UUID.fromString(checkinId)).stream()
                .map(mapper::toFotoDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteFoto(String fotoId) {
        fotoJpaRepository.deleteById(UUID.fromString(fotoId));
    }

    public Garantia saveGarantia(Garantia garantia) {
        return mapper.toGarantiaDomain(garantiaJpaRepository.save(mapper.toGarantiaEntity(garantia)));
    }

    public Optional<Garantia> findGarantiaById(String id) {
        return garantiaJpaRepository.findById(UUID.fromString(id)).map(mapper::toGarantiaDomain);
    }

    public List<Garantia> findGarantiasByTenantId(String tenantId) {
        return garantiaJpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toGarantiaDomain).collect(Collectors.toList());
    }
}
