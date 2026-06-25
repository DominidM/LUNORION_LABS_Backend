package com.lunorion.labs.core.ordencompra.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.ordencompra.domain.entity.OrdenCompraItem;
import com.lunorion.labs.core.ordencompra.domain.ports.out.IOrdenCompraItemRepositoryPort;
import com.lunorion.labs.core.ordencompra.infrastructure.adapters.out.persistence.mapper.OrdenCompraEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class OrdenCompraItemRepositoryAdapter implements IOrdenCompraItemRepositoryPort {

    private final OrdenCompraItemJpaRepository jpaRepository;
    private final OrdenCompraEntityMapper mapper;

    public OrdenCompraItemRepositoryAdapter(OrdenCompraItemJpaRepository jpaRepository, OrdenCompraEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public OrdenCompraItem save(OrdenCompraItem item) {
        return mapper.toItemDomain(jpaRepository.save(mapper.toItemEntity(item)));
    }

    @Override
    public Optional<OrdenCompraItem> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toItemDomain);
    }

    @Override
    public List<OrdenCompraItem> findByOrdenCompraId(String ordenCompraId) {
        return jpaRepository.findByOrdenCompraId(UUID.fromString(ordenCompraId)).stream()
                .map(mapper::toItemDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(UUID.fromString(id));
    }
}
