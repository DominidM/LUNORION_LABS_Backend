package com.lunorion.labs.core.vehiculo.infrastructure.adapters.out.persistence.mapper;

import com.lunorion.labs.core.vehiculo.domain.entity.Vehiculo;
import com.lunorion.labs.core.vehiculo.infrastructure.adapters.out.persistence.entity.VehiculoEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VehiculoEntityMapper {

    public VehiculoEntity toEntity(Vehiculo domain) {
        VehiculoEntity entity = new VehiculoEntity();
        entity.setId(domain.getId());
        entity.setTenantId(UUID.fromString(domain.getTenantId()));
        entity.setPlaca(domain.getPlaca());
        entity.setMarca(domain.getMarca());
        entity.setModelo(domain.getModelo());
        entity.setAnio(domain.getAnio());
        entity.setColor(domain.getColor());
        entity.setNumeroChasis(domain.getNumeroChasis());
        entity.setNumeroMotor(domain.getNumeroMotor());
        entity.setClienteId(domain.getClienteId() != null ? UUID.fromString(domain.getClienteId()) : null);
        entity.setActivo(domain.isActivo());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    public Vehiculo toDomain(VehiculoEntity entity) {
        Vehiculo vehiculo = new Vehiculo(
                entity.getId(),
                entity.getTenantId().toString(),
                entity.getPlaca(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getAnio(),
                entity.getColor(),
                entity.getNumeroChasis(),
                entity.getNumeroMotor(),
                entity.getClienteId() != null ? entity.getClienteId().toString() : null,
                entity.isActivo()
        );
        vehiculo.setCreatedAt(entity.getCreatedAt());
        vehiculo.setUpdatedAt(entity.getUpdatedAt());
        return vehiculo;
    }
}
