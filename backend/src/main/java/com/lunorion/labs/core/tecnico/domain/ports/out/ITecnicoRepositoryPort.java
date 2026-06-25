package com.lunorion.labs.core.tecnico.domain.ports.out;

import com.lunorion.labs.core.tecnico.domain.entity.Tecnico;
import com.lunorion.labs.core.tecnico.domain.entity.ConfiguracionComision;

import java.util.List;
import java.util.Optional;

public interface ITecnicoRepositoryPort {
    Tecnico save(Tecnico tecnico);
    Optional<Tecnico> findById(String id);
    List<Tecnico> findAll();
    List<Tecnico> findByTenantId(String tenantId);
    void deleteById(String id);

    ConfiguracionComision saveComision(ConfiguracionComision comision);
    Optional<ConfiguracionComision> findComisionById(String id);
    List<ConfiguracionComision> findComisionesByTecnicoId(String tecnicoId);
}
