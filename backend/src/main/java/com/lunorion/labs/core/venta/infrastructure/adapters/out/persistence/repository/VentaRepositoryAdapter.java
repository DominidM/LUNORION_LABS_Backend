package com.lunorion.labs.core.venta.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.venta.domain.entity.Venta;
import com.lunorion.labs.core.venta.domain.entity.VentaItem;
import com.lunorion.labs.core.venta.domain.ports.out.IVentaRepositoryPort;
import com.lunorion.labs.core.venta.infrastructure.adapters.out.persistence.mapper.VentaEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class VentaRepositoryAdapter implements IVentaRepositoryPort {

    private final VentaJpaRepository jpaRepository;
    private final VentaItemJpaRepository itemJpaRepository;
    private final VentaEntityMapper mapper;

    public VentaRepositoryAdapter(VentaJpaRepository jpaRepository, VentaItemJpaRepository itemJpaRepository, VentaEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.itemJpaRepository = itemJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Venta save(Venta venta) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(venta)));
    }

    @Override
    public VentaItem saveItem(VentaItem item) {
        return mapper.toItemDomain(itemJpaRepository.save(mapper.toItemEntity(item)));
    }

    @Override
    public List<VentaItem> saveAllItems(List<VentaItem> items) {
        return items.stream()
                .map(this::saveItem)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Venta> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<Venta> findByClienteId(String clienteId) {
        return jpaRepository.findByClienteId(UUID.fromString(clienteId)).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> findByTenantId(String tenantId) {
        return jpaRepository.findByTenantId(UUID.fromString(tenantId)).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(UUID.fromString(id));
    }
}
