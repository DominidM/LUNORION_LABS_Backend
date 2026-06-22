package com.lunorion.labs.core.usuario.application.service.command;

import com.lunorion.labs.core.usuario.application.dto.in.CreateUsuarioRequest;
import com.lunorion.labs.core.usuario.application.dto.out.UsuarioResponse;
import com.lunorion.labs.core.usuario.application.mapper.UsuarioMapper;
import com.lunorion.labs.core.usuario.domain.entity.Usuario;
import com.lunorion.labs.core.usuario.domain.ports.in.IUsuarioCommandPort;
import com.lunorion.labs.core.usuario.domain.ports.out.IUsuarioRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioCommandService implements IUsuarioCommandPort {

    private final IUsuarioRepositoryPort repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioCommandService(IUsuarioRepositoryPort repository, UsuarioMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioResponse create(CreateUsuarioRequest request) {
        Usuario usuario = mapper.toDomain(request);
        usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        Usuario saved = repository.save(usuario);
        return mapper.toResponse(saved);
    }

    @Override
    public void desactivar(String id) {
        repository.findById(id).ifPresent(usuario -> {
            usuario.desactivar();
            repository.save(usuario);
        });
    }
}
