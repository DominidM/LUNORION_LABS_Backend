package com.lunorion.labs.core.venta.application.mapper;

import com.lunorion.labs.core.venta.application.dto.in.CreateVentaRequest;
import com.lunorion.labs.core.venta.application.dto.out.VentaResponse;
import com.lunorion.labs.core.venta.application.dto.out.VentaResponse.VentaItemResponse;
import com.lunorion.labs.core.venta.domain.entity.Venta;
import com.lunorion.labs.core.venta.domain.entity.VentaItem;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VentaMapper {

    public Venta toDomain(CreateVentaRequest request) {
        return Venta.create(
            request.getTenantId(),
            request.getClienteId(),
            request.getTipo(),
            request.getSubtotal(),
            request.getIgv(),
            request.getTotal(),
            null
        );
    }

    public VentaItem toItemDomain(String ventaId, CreateVentaRequest.CreateVentaItemRequest request) {
        return VentaItem.create(
            ventaId,
            request.getProductoId(),
            request.getCantidad(),
            request.getPrecioUnitario(),
            request.getDescuento(),
            request.getSubtotal()
        );
    }

    public List<VentaItem> toItemsDomain(String ventaId, List<CreateVentaRequest.CreateVentaItemRequest> requests) {
        if (requests == null) return Collections.emptyList();
        return requests.stream()
                .map(r -> toItemDomain(ventaId, r))
                .collect(Collectors.toList());
    }

    public VentaResponse toResponse(Venta venta) {
        VentaResponse response = new VentaResponse();
        response.setId(venta.getId().toString());
        response.setTenantId(venta.getTenantId());
        response.setClienteId(venta.getClienteId());
        response.setTipo(venta.getTipo());
        response.setSubtotal(venta.getSubtotal());
        response.setIgv(venta.getIgv());
        response.setTotal(venta.getTotal());
        response.setDescuento(venta.getDescuento());
        response.setMetodoPago(venta.getMetodoPago());
        response.setEstadoPago(venta.getEstadoPago());
        response.setUsuarioId(venta.getUsuarioId());
        return response;
    }

    public VentaItemResponse toItemResponse(VentaItem item) {
        VentaItemResponse r = new VentaItemResponse();
        r.setId(item.getId().toString());
        r.setProductoId(item.getProductoId());
        r.setCantidad(item.getCantidad());
        r.setPrecioUnitario(item.getPrecioUnitario());
        r.setDescuento(item.getDescuento());
        r.setSubtotal(item.getSubtotal());
        return r;
    }

    public List<VentaItemResponse> toItemsResponse(List<VentaItem> items) {
        if (items == null) return Collections.emptyList();
        return items.stream().map(this::toItemResponse).collect(Collectors.toList());
    }
}
