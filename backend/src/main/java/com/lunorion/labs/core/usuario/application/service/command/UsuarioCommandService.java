package com.lunorion.labs.core.usuario.application.service.command;

import com.lunorion.labs.core.usuario.application.dto.in.AsignarPermisosRequest;
import com.lunorion.labs.core.usuario.application.dto.in.CreateUsuarioRequest;
import com.lunorion.labs.core.usuario.application.dto.in.UpdateUsuarioRequest;
import com.lunorion.labs.core.usuario.application.dto.out.UsuarioResponse;
import com.lunorion.labs.core.usuario.application.mapper.UsuarioMapper;
import com.lunorion.labs.core.usuario.domain.entity.Permiso;
import com.lunorion.labs.core.usuario.domain.entity.Usuario;
import com.lunorion.labs.core.usuario.domain.ports.in.IUsuarioCommandPort;
import com.lunorion.labs.core.usuario.domain.ports.out.IPermisoRepositoryPort;
import com.lunorion.labs.core.usuario.domain.ports.out.IUsuarioRepositoryPort;
import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.entity.UsuarioPermisoEntity;
import com.lunorion.labs.core.usuario.infrastructure.adapters.out.persistence.repository.UsuarioPermisoJpaRepository;
import com.lunorion.labs.shared.domain.Rol;
import com.lunorion.labs.shared.infrastructure.security.SecurityContextHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UsuarioCommandService implements IUsuarioCommandPort {

    private final IUsuarioRepositoryPort repository;
    private final IPermisoRepositoryPort permisoRepository;
    private final UsuarioPermisoJpaRepository usuarioPermisoJpaRepository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioCommandService(IUsuarioRepositoryPort repository,
                                 IPermisoRepositoryPort permisoRepository,
                                 UsuarioPermisoJpaRepository usuarioPermisoJpaRepository,
                                 UsuarioMapper mapper,
                                 PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.permisoRepository = permisoRepository;
        this.usuarioPermisoJpaRepository = usuarioPermisoJpaRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioResponse create(CreateUsuarioRequest request) {
        Rol requested = validateAndAuthorizeRol(request.getRol());
        Usuario usuario = mapper.toDomain(request);
        usuario.setRol(requested.name());
        usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        Usuario saved = repository.save(usuario);
        return mapper.toResponse(saved);
    }

    @Override
    public UsuarioResponse update(String id, UpdateUsuarioRequest request) {
        return repository.findById(id).map(usuario -> {
            if (request.getRol() != null) {
                Rol requested = validateAndAuthorizeRol(request.getRol());
                request.setRol(requested.name());
            }
            mapper.updateDomain(usuario, request);
            if (request.getPassword() != null && !request.getPassword().isBlank()) {
                usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
            }
            Usuario saved = repository.save(usuario);
            return mapper.toResponse(saved);
        }).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id));
    }

    @Override
    public void desactivar(String id) {
        repository.findById(id).ifPresent(usuario -> {
            usuario.desactivar();
            repository.save(usuario);
        });
    }

    @Override
    public void activar(String id) {
        repository.findById(id).ifPresent(usuario -> {
            usuario.activar();
            repository.save(usuario);
        });
    }

    @Override
    public void asignarPermisos(String usuarioId, AsignarPermisosRequest request) {
        UUID uid = UUID.fromString(usuarioId);
        usuarioPermisoJpaRepository.deleteByUsuarioId(uid);
        if (request.getPermisos() != null) {
            List<Permiso> permisos = permisoRepository.findAllByCodigoIn(request.getPermisos());
            permisos.forEach(p -> {
                UsuarioPermisoEntity up = new UsuarioPermisoEntity();
                up.setUsuarioId(uid);
                up.setPermisoId(p.getId());
                usuarioPermisoJpaRepository.save(up);
            });
        }
    }

    private Rol validateAndAuthorizeRol(String rolNombre) {
        Rol requested = Rol.from(rolNombre);
        if (requested == null) {
            throw new IllegalArgumentException("Rol inválido: " + rolNombre
                    + ". Valores permitidos: " + java.util.Arrays.toString(Rol.values()));
        }
        Rol caller = Rol.from(SecurityContextHelper.currentRol());
        if (caller == null || !caller.canAssign(requested)) {
            throw new AccessDeniedException("No autorizado para asignar el rol " + requested);
        }
        return requested;
    }
}
