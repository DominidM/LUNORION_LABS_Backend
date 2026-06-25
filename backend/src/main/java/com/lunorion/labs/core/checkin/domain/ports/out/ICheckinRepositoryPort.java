package com.lunorion.labs.core.checkin.domain.ports.out;

import com.lunorion.labs.core.checkin.domain.entity.Checkin;
import com.lunorion.labs.core.checkin.domain.entity.CheckinFoto;

import java.util.List;
import java.util.Optional;

public interface ICheckinRepositoryPort {
    Checkin save(Checkin checkin);
    Optional<Checkin> findById(String id);
    List<Checkin> findByTenantId(String tenantId);
    Optional<Checkin> findByOtId(String otId);
    void deleteById(String id);

    CheckinFoto saveFoto(CheckinFoto foto);
    void deleteFoto(String fotoId);
    List<CheckinFoto> findFotosByCheckinId(String checkinId);
}
