package com.lunorion.labs.core.dashboard.application.service;

import com.lunorion.labs.core.dashboard.application.dto.out.KpiResponse;
import com.lunorion.labs.core.dashboard.application.dto.out.RentabilidadResponse;
import com.lunorion.labs.core.dashboard.domain.ports.in.IDashboardQueryPort;
import com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.entity.OrdenTrabajoEntity;
import com.lunorion.labs.core.orden_trabajo.infrastructure.adapters.out.persistence.repository.OrdenTrabajoJpaRepository;
import com.lunorion.labs.core.cita.infrastructure.adapters.out.persistence.repository.CitaJpaRepository;
import com.lunorion.labs.core.producto.infrastructure.adapters.out.persistence.repository.ProductoJpaRepository;
import com.lunorion.labs.core.venta.infrastructure.adapters.out.persistence.repository.VentaJpaRepository;
import com.lunorion.labs.core.caja.infrastructure.adapters.out.persistence.repository.MovimientoCajaJpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DashboardService implements IDashboardQueryPort {

    private final OrdenTrabajoJpaRepository ordenTrabajoJpaRepository;
    private final CitaJpaRepository citaJpaRepository;
    private final ProductoJpaRepository productoJpaRepository;
    private final VentaJpaRepository ventaJpaRepository;
    private final MovimientoCajaJpaRepository movimientoCajaJpaRepository;

    public DashboardService(OrdenTrabajoJpaRepository ordenTrabajoJpaRepository,
                            CitaJpaRepository citaJpaRepository,
                            ProductoJpaRepository productoJpaRepository,
                            VentaJpaRepository ventaJpaRepository,
                            MovimientoCajaJpaRepository movimientoCajaJpaRepository) {
        this.ordenTrabajoJpaRepository = ordenTrabajoJpaRepository;
        this.citaJpaRepository = citaJpaRepository;
        this.productoJpaRepository = productoJpaRepository;
        this.ventaJpaRepository = ventaJpaRepository;
        this.movimientoCajaJpaRepository = movimientoCajaJpaRepository;
    }

    @Override
    public KpiResponse obtenerKpis(String tenantId) {
        UUID tenantUUID = UUID.fromString(tenantId);
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);
        LocalDateTime startOfMonth = today.withDayOfMonth(1).atStartOfDay();
        LocalDateTime endOfMonth = today.withDayOfMonth(today.lengthOfMonth()).atTime(LocalTime.MAX);

        BigDecimal facturacionHoy = ventaJpaRepository
                .findByTenantIdAndCreatedAtBetween(tenantUUID, startOfDay, endOfDay)
                .stream()
                .map(v -> v.getTotal() != null ? v.getTotal() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long ordenesAbiertas = ordenTrabajoJpaRepository.findAll().stream()
                .filter(o -> tenantUUID.equals(o.getTenantId()))
                .filter(o -> {
                    String est = o.getEstado();
                    return est != null && List.of("PENDIENTE", "EN_PROCESO", "EN_REVISION").contains(est.toUpperCase());
                })
                .count();

        long stockCritico = productoJpaRepository.findStockCritico(tenantUUID).size();

        long citasHoy = citaJpaRepository
                .findByTenantIdAndFechaHoraBetween(tenantUUID, startOfDay, endOfDay)
                .size();

        BigDecimal ingresosMes = ventaJpaRepository
                .findByTenantIdAndCreatedAtBetween(tenantUUID, startOfMonth, endOfMonth)
                .stream()
                .map(v -> v.getTotal() != null ? v.getTotal() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal egresosMes = movimientoCajaJpaRepository
                .findByTenantIdAndTipoAndCreatedAtBetween(tenantUUID, "SALIDA", startOfMonth, endOfMonth)
                .stream()
                .map(m -> m.getMonto() != null ? m.getMonto() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new KpiResponse(facturacionHoy, ordenesAbiertas, stockCritico, citasHoy, ingresosMes, egresosMes);
    }

    @Override
    public List<RentabilidadResponse> obtenerRentabilidad(String tenantId) {
        UUID tenantUUID = UUID.fromString(tenantId);
        List<OrdenTrabajoEntity> ordenes = ordenTrabajoJpaRepository.findByTenantId(tenantUUID);

        BigDecimal totalIngresos = ordenes.stream()
                .map(o -> o.getTotal() != null ? o.getTotal() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCostos = ordenes.stream()
                .map(o -> {
                    BigDecimal r = o.getTotalRepuestos() != null ? o.getTotalRepuestos() : BigDecimal.ZERO;
                    BigDecimal m = o.getTotalManoObra() != null ? o.getTotalManoObra() : BigDecimal.ZERO;
                    return r.add(m);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal margen = totalIngresos.subtract(totalCostos);
        double porcentaje = totalIngresos.compareTo(BigDecimal.ZERO) > 0
                ? margen.multiply(BigDecimal.valueOf(100)).divide(totalIngresos, 2, java.math.RoundingMode.HALF_UP).doubleValue()
                : 0.0;

        RentabilidadResponse general = new RentabilidadResponse("General", totalIngresos, totalCostos, margen, porcentaje);

        return Arrays.asList(
                general,
                new RentabilidadResponse("Mantenimiento Preventivo", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0.0),
                new RentabilidadResponse("Reparación General", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0.0)
        );
    }

    @Override
    public byte[] exportarReporte(String tenantId, String formato, String fechaInicio,
                                  String fechaFin, String tipo) {
        StringBuilder contenido = new StringBuilder();
        contenido.append("Reporte de ").append(tipo).append("\n");
        contenido.append("Tenant: ").append(tenantId).append("\n");
        contenido.append("Periodo: ").append(fechaInicio).append(" al ").append(fechaFin).append("\n");
        contenido.append("Formato: ").append(formato).append("\n");
        contenido.append("Generado: ").append(LocalDateTime.now()).append("\n\n");

        if ("OT".equalsIgnoreCase(tipo) || "TODOS".equalsIgnoreCase(tipo)) {
            List<OrdenTrabajoEntity> ordenes = ordenTrabajoJpaRepository.findByTenantId(UUID.fromString(tenantId));
            contenido.append("--- ORDENES DE TRABAJO ---\n");
            for (OrdenTrabajoEntity ot : ordenes) {
                contenido.append(ot.getNumeroOt()).append(" | ")
                        .append(ot.getEstado()).append(" | S/.")
                        .append(ot.getTotal() != null ? ot.getTotal() : "0").append("\n");
            }
        }

        return contenido.toString().getBytes();
    }
}
