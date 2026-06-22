package com.lunorion.labs.core.inventario.application.mapper;

import com.lunorion.labs.core.inventario.application.dto.in.CreateMovimientoStockRequest;
import com.lunorion.labs.core.inventario.application.dto.out.MovimientoStockResponse;
import com.lunorion.labs.core.inventario.domain.entity.MovimientoStock;
import org.springframework.stereotype.Component;

@Component
public class MovimientoStockMapper {

    public MovimientoStock toDomain(CreateMovimientoStockRequest request) {
        return MovimientoStock.create(
            request.getTenantId(),
            request.getProductoId(),
            request.getTipo(),
            request.getSubtipo(),
            request.getCantidad(),
            request.getCostoUnitario(),
            request.getStockAnterior(),
            request.getStockPosterior(),
            request.getDocumentoOrigen(),
            request.getObservacion(),
            request.getUsuarioId()
        );
    }

    public MovimientoStockResponse toResponse(MovimientoStock movimiento) {
        MovimientoStockResponse response = new MovimientoStockResponse();
        response.setId(movimiento.getId().toString());
        response.setTenantId(movimiento.getTenantId());
        response.setProductoId(movimiento.getProductoId());
        response.setTipo(movimiento.getTipo());
        response.setSubtipo(movimiento.getSubtipo());
        response.setCantidad(movimiento.getCantidad());
        response.setCostoUnitario(movimiento.getCostoUnitario());
        response.setStockAnterior(movimiento.getStockAnterior());
        response.setStockPosterior(movimiento.getStockPosterior());
        response.setDocumentoOrigen(movimiento.getDocumentoOrigen());
        response.setObservacion(movimiento.getObservacion());
        response.setUsuarioId(movimiento.getUsuarioId());
        return response;
    }
}
