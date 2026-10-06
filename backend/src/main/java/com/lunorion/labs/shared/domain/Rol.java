package com.lunorion.labs.shared.domain;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public enum Rol {

    SUPER_ADMIN,
    ADMIN,
    PUBLIC;

    public static Rol from(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Rol.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public boolean canAssign(Rol target) {
        if (target == null) {
            return false;
        }
        return switch (this) {
            case SUPER_ADMIN -> true;
            case ADMIN -> target == ADMIN || target == PUBLIC;
            case PUBLIC -> false;
        };
    }

    public static Set<Rol> assignableBy(Rol caller) {
        EnumSet<Rol> assignable = EnumSet.noneOf(Rol.class);
        if (caller != null) {
            for (Rol rol : values()) {
                if (caller.canAssign(rol)) {
                    assignable.add(rol);
                }
            }
        }
        return assignable;
    }

    public static List<String> assignableNames(Rol caller) {
        return assignableBy(caller).stream().map(Enum::name).toList();
    }
}
