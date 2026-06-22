package com.lunorion.labs.core.ordencompra.application.mapper;

import com.lunorion.labs.core.ordencompra.application.dto.in.CreateOrdenCompraRequest;
import com.lunorion.labs.core.ordencompra.application.dto.out.OrdenCompraItemResponse;
import com.lunorion.labs.core.ordencompra.application.dto.out.OrdenCompraResponse;
import com.lunorion.labs.core.ordencompra.domain.entity.OrdenCompra;
import com.lunorion.labs.core.ordencompra.domain.entity.OrdenCompraItem;
import org.springframework.stereotype.Component;

@Component
public class OrdenCompraMapper {

    public OrdenCompra toDomain(CreateOrdenCompraRequest request) {
        return OrdenCompra.create(
            request.getTenantId(),
            request.getProveedorId(),
            request.getNumeroOrden(),
            request.getFechaEmision(),
            request.getEstado(),
            request.getTotal(),
            request.getObservacion(),
            request.getUsuarioId()
        );
    }

    public OrdenCompraResponse toResponse(OrdenCompra ordenCompra) {
        OrdenCompraResponse response = new OrdenCompraResponse();
        response.setId(ordenCompra.getId().toString());
        response.setTenantId(ordenCompra.getTenantId());
        response.setProveedorId(ordenCompra.getProveedorId());
        response.setNumeroOrden(ordenCompra.getNumeroOrden());
        response.setFechaEmision(ordenCompra.getFechaEmision());
        response.setEstado(ordenCompra.getEstado());
        response.setTotal(ordenCompra.getTotal());
        response.setObservacion(ordenCompra.getObservacion());
        response.setUsuarioId(ordenCompra.getUsuarioId());
        return response;
    }

    public OrdenCompraItemResponse toItemResponse(OrdenCompraItem item) {
        OrdenCompraItemResponse response = new OrdenCompraItemResponse();
        response.setId(item.getId().toString());
        response.setOrdenCompraId(item.getOrdenCompraId());
        response.setProductoId(item.getProductoId());
        response.setCantidad(item.getCantidad());
        response.setCantidadRecibida(item.getCantidadRecibida());
        response.setPrecioUnitario(item.getPrecioUnitario());
        response.setSubtotal(item.getSubtotal());
        return response;
    }
}
