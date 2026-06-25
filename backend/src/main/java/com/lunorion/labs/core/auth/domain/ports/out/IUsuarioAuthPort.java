package com.lunorion.labs.core.auth.domain.ports.out;

import java.util.Optional;

public interface IUsuarioAuthPort {
    Optional<AuthUser> findByEmail(String email);
    boolean validarPassword(String rawPassword, String hash);
}
