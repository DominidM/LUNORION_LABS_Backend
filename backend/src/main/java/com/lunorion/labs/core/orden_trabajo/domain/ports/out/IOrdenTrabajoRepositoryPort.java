package com.lunorion.labs.core.orden_trabajo.domain.ports.out;

import com.lunorion.labs.core.orden_trabajo.domain.entity.OrdenTrabajo;
import com.lunorion.labs.core.orden_trabajo.domain.entity.OtInsumo;
import com.lunorion.labs.core.orden_trabajo.domain.entity.OtManoObra;
import com.lunorion.labs.core.orden_trabajo.domain.filter.OrdenTrabajoFiltro;
import com.lunorion.labs.shared.domain.PageResult;

import java.util.List;
import java.util.Optional;

public interface IOrdenTrabajoRepositoryPort {
    Optional<OrdenTrabajo> findById(String id);
    List<OrdenTrabajo> findByTenantId(String tenantId);
    List<OrdenTrabajo> findByEstado(String estado, String tenantId);
    List<OrdenTrabajo> findAll();
    PageResult<OrdenTrabajo> search(OrdenTrabajoFiltro filtro);
    List<OrdenTrabajo> searchAll(OrdenTrabajoFiltro filtro);
    OrdenTrabajo save(OrdenTrabajo ordenTrabajo);
    void deleteById(String id);
    void saveAllInsumos(List<OtInsumo> insumos);
    void deleteInsumoById(String insumoId);
    void saveAllManoObra(List<OtManoObra> manosObra);
    void deleteManoObraById(String laborId);
    Optional<OtInsumo> findInsumoById(String id);
    List<OtInsumo> findInsumosByOrdenTrabajoId(String ordenTrabajoId);
    Optional<OtManoObra> findManoObraById(String id);
    List<OtManoObra> findManosObraByOrdenTrabajoId(String ordenTrabajoId);
    void saveInsumo(OtInsumo insumo);
    void saveManoObra(OtManoObra manoObra);
}
