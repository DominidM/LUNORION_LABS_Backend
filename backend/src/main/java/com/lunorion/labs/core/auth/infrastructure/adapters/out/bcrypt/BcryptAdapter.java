package com.lunorion.labs.core.auth.infrastructure.adapters.out.bcrypt;

import com.lunorion.labs.core.auth.domain.ports.out.AuthUser;
import com.lunorion.labs.core.auth.domain.ports.out.IUsuarioAuthPort;
import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.entity.UsuarioEntity;
import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.repository.PermisoJpaRepository;
import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.repository.UsuarioJpaRepository;
import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.repository.UsuarioPermisoJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
public class BcryptAdapter implements IUsuarioAuthPort {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioJpaRepository usuarioRepo;
    private final UsuarioPermisoJpaRepository usuarioPermisoRepo;
    private final PermisoJpaRepository permisoRepo;

    public BcryptAdapter(PasswordEncoder passwordEncoder, UsuarioJpaRepository usuarioRepo,
                         UsuarioPermisoJpaRepository usuarioPermisoRepo, PermisoJpaRepository permisoRepo) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepo = usuarioRepo;
        this.usuarioPermisoRepo = usuarioPermisoRepo;
        this.permisoRepo = permisoRepo;
    }

    @Override
    public Optional<AuthUser> findByEmail(String email) {
        return usuarioRepo.findByEmail(email).map(this::toAuthUser);
    }

    private AuthUser toAuthUser(UsuarioEntity entity) {
        List<String> permisos = usuarioPermisoRepo.findByUsuarioId(entity.getId()).stream()
                .map(up -> permisoRepo.findById(up.getPermisoId()))
                .filter(Optional::isPresent)
                .map(p -> p.get().getCodigo())
                .collect(Collectors.toList());
        return new AuthUser(
                entity.getId().toString(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getTenantId().toString(),
                entity.getRol(),
                entity.getActivo(),
                entity.getNombres(),
                entity.getApellidos(),
                permisos
        );
    }

    @Override
    public boolean validarPassword(String rawPassword, String hash) {
        return passwordEncoder.matches(rawPassword, hash);
    }
}
