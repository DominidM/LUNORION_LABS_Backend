package com.lunorion.labs.core.usuario.application.mapper;

import com.lunorion.labs.core.usuario.application.dto.in.CreateUsuarioRequest;
import com.lunorion.labs.core.usuario.application.dto.out.UsuarioResponse;
import com.lunorion.labs.core.usuario.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toDomain(CreateUsuarioRequest request) {
        return Usuario.create(
            request.getTenantId(),
            request.getEmail(),
            request.getNombres(),
            request.getApellidos(),
            request.getDni(),
            request.getTelefono(),
            request.getRol()
        );
    }

    public UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId().toString());
        response.setEmail(usuario.getEmail());
        response.setNombres(usuario.getNombres());
        response.setApellidos(usuario.getApellidos());
        response.setDni(usuario.getDni());
        response.setRol(usuario.getRol());
        response.setActivo(usuario.isActivo());
        return response;
    }
}
