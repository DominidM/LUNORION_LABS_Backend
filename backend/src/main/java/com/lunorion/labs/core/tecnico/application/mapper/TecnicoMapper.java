package com.lunorion.labs.core.tecnico.application.mapper;

import com.lunorion.labs.core.tecnico.application.dto.in.CreateTecnicoRequest;
import com.lunorion.labs.core.tecnico.application.dto.out.ConfiguracionComisionResponse;
import com.lunorion.labs.core.tecnico.application.dto.out.TecnicoResponse;
import com.lunorion.labs.core.tecnico.domain.entity.ConfiguracionComision;
import com.lunorion.labs.core.tecnico.domain.entity.Tecnico;
import org.springframework.stereotype.Component;

@Component
public class TecnicoMapper {

    public Tecnico toDomain(CreateTecnicoRequest request) {
        return Tecnico.create(
            request.getTenantId(),
            request.getUsuarioId(),
            request.getEspecialidades(),
            request.getTarifaHora(),
            request.getNumeroContacto()
        );
    }

    public TecnicoResponse toResponse(Tecnico tecnico) {
        TecnicoResponse response = new TecnicoResponse();
        response.setId(tecnico.getId().toString());
        response.setTenantId(tecnico.getTenantId());
        response.setUsuarioId(tecnico.getUsuarioId());
        response.setEspecialidades(tecnico.getEspecialidades());
        response.setTarifaHora(tecnico.getTarifaHora());
        response.setNumeroContacto(tecnico.getNumeroContacto());
        response.setFechaIngreso(tecnico.getFechaIngreso());
        response.setActivo(tecnico.isActivo());
        return response;
    }

    public ConfiguracionComisionResponse toComisionResponse(ConfiguracionComision comision) {
        ConfiguracionComisionResponse response = new ConfiguracionComisionResponse();
        response.setId(comision.getId().toString());
        response.setTenantId(comision.getTenantId());
        response.setTecnicoId(comision.getTecnicoId());
        response.setProductoId(comision.getProductoId());
        response.setTipo(comision.getTipo().name());
        response.setPorcentaje(comision.getPorcentaje());
        response.setActivo(comision.isActivo());
        return response;
    }
}
