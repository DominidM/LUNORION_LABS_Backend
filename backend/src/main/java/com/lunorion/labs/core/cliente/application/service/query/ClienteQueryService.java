package com.lunorion.labs.core.cliente.application.service.query;

import com.lunorion.labs.core.cliente.application.dto.out.ClienteResponse;
import com.lunorion.labs.core.cliente.application.dto.out.HistorialCompraResponse;
import com.lunorion.labs.core.cliente.application.dto.out.HistorialTrabajoResponse;
import com.lunorion.labs.core.cliente.application.dto.out.RentabilidadClienteResponse;
import com.lunorion.labs.core.cliente.application.mapper.ClienteMapper;
import com.lunorion.labs.core.cliente.domain.ports.in.IClienteQueryPort;
import com.lunorion.labs.core.cliente.domain.ports.out.IClienteRepositoryPort;
import com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.entity.OrdenTrabajoEntity;
import com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.repository.OrdenTrabajoJpaRepository;
import com.lunorion.labs.core.venta.infrastructure.adapters.out.persistence.entity.VentaEntity;
import com.lunorion.labs.core.venta.infrastructure.adapters.out.persistence.repository.VentaJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ClienteQueryService implements IClienteQueryPort {

    private final IClienteRepositoryPort repository;
    private final ClienteMapper mapper;
    private final OrdenTrabajoJpaRepository ordenTrabajoJpaRepository;
    private final VentaJpaRepository ventaJpaRepository;

    public ClienteQueryService(IClienteRepositoryPort repository, ClienteMapper mapper,
                               OrdenTrabajoJpaRepository ordenTrabajoJpaRepository,
                               VentaJpaRepository ventaJpaRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.ordenTrabajoJpaRepository = ordenTrabajoJpaRepository;
        this.ventaJpaRepository = ventaJpaRepository;
    }

    @Override
    public Optional<ClienteResponse> findById(String id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public Optional<ClienteResponse> findByNumeroDocumento(String numeroDocumento) {
        return repository.findByNumeroDocumento(numeroDocumento).map(mapper::toResponse);
    }

    @Override
    public List<ClienteResponse> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistorialTrabajoResponse> workHistory(String id) {
        UUID clienteId = UUID.fromString(id);
        return ordenTrabajoJpaRepository.findByClienteId(clienteId).stream()
                .map(this::toHistorialTrabajo)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistorialCompraResponse> purchaseHistory(String id) {
        UUID clienteId = UUID.fromString(id);
        return ventaJpaRepository.findByClienteId(clienteId).stream()
                .map(this::toHistorialCompra)
                .collect(Collectors.toList());
    }

    @Override
    public RentabilidadClienteResponse profitability(String id) {
        UUID clienteId = UUID.fromString(id);
        List<OrdenTrabajoEntity> ordenes = ordenTrabajoJpaRepository.findByClienteId(clienteId);
        BigDecimal totalFacturado = ordenes.stream()
                .map(OrdenTrabajoEntity::getTotal)
                .filter(t -> t != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCostos = ordenes.stream()
                .map(o -> {
                    BigDecimal repuestos = o.getTotalRepuestos() != null ? o.getTotalRepuestos() : BigDecimal.ZERO;
                    BigDecimal manoObra = o.getTotalManoObra() != null ? o.getTotalManoObra() : BigDecimal.ZERO;
                    return repuestos.add(manoObra);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        long completadas = ordenes.stream().filter(o -> "COMPLETADA".equalsIgnoreCase(o.getEstado())).count();

        String nombreCliente = repository.findById(id)
                .map(c -> {
                    String nom = c.getNombres() != null ? c.getNombres() : "";
                    String ape = c.getApellidos() != null ? c.getApellidos() : "";
                    return (nom + " " + ape).trim();
                })
                .orElse("");

        RentabilidadClienteResponse r = new RentabilidadClienteResponse();
        r.setClienteId(id);
        r.setNombreCliente(nombreCliente);
        r.setTotalFacturado(totalFacturado);
        r.setTotalCostos(totalCostos);
        r.setMargen(totalFacturado.subtract(totalCostos));
        r.setOrdenesCompletadas((int) completadas);
        return r;
    }

    private HistorialTrabajoResponse toHistorialTrabajo(OrdenTrabajoEntity entity) {
        HistorialTrabajoResponse r = new HistorialTrabajoResponse();
        r.setId(entity.getId().toString());
        r.setOrdenTrabajoId(entity.getNumeroOt());
        r.setDescripcion(entity.getMotivoIngreso());
        r.setEstado(entity.getEstado());
        r.setFechaCreacion(entity.getCreatedAt());
        return r;
    }

    private HistorialCompraResponse toHistorialCompra(VentaEntity entity) {
        HistorialCompraResponse r = new HistorialCompraResponse();
        r.setId(entity.getId().toString());
        r.setVentaId(entity.getId().toString());
        r.setTotal(entity.getTotal());
        r.setFecha(entity.getCreatedAt());
        return r;
    }
}
