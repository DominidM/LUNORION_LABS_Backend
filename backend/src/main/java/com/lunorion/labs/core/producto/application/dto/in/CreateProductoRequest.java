package com.lunorion.labs.core.producto.application.dto.in;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProductoRequest {
    private String tenantId;
    private UUID categoriaId;
    private String codigo;
    private String codigoBarra;
    private String nombre;
    private String descripcion;
    private String marca;
    private String modelo;
    private String unidadMedida;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private Integer stockActual;
    private Integer stockMinimo;
    private String ubicacion;
    private String tipo;

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public UUID getCategoriaId() { return categoriaId; }
    public void setCategoriaId(UUID categoriaId) { this.categoriaId = categoriaId; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getCodigoBarra() { return codigoBarra; }
    public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
    public BigDecimal getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(BigDecimal precioCompra) { this.precioCompra = precioCompra; }
    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }
    public Integer getStockActual() { return stockActual; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }
    public Integer getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
