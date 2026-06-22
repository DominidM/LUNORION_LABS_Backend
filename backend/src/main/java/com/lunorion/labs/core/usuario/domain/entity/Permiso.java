package com.lunorion.labs.core.usuario.domain.entity;

import com.lunorion.labs.shared.domain.BaseEntity;
import java.util.UUID;

public class Permiso extends BaseEntity {
    private String codigo;
    private String nombre;
    private String modulo;

    public Permiso() {}

    public Permiso(UUID id, String codigo, String nombre, String modulo) {
        super(id);
        this.codigo = codigo;
        this.nombre = nombre;
        this.modulo = modulo;
    }

    public static Permiso create(String codigo, String nombre, String modulo) {
        return new Permiso(UUID.randomUUID(), codigo, nombre, modulo);
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getModulo() { return modulo; }
}
