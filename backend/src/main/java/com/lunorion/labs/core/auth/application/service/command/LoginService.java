package com.lunorion.labs.core.auth.application.service.command;

import com.lunorion.labs.core.auth.application.dto.in.LoginRequest;
import com.lunorion.labs.core.auth.application.dto.out.LoginResponse;
import com.lunorion.labs.core.auth.domain.ports.in.ILoginPort;
import com.lunorion.labs.core.auth.domain.ports.out.IUsuarioAuthPort;
import com.lunorion.labs.shared.infrastructure.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginPort {

    private final IUsuarioAuthPort usuarioAuthPort;
    private final JwtTokenProvider tokenProvider;

    public LoginService(IUsuarioAuthPort usuarioAuthPort, JwtTokenProvider tokenProvider) {
        this.usuarioAuthPort = usuarioAuthPort;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        var userOpt = usuarioAuthPort.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Credenciales invalidas");
        }
        var user = userOpt.get();
        if (!user.isActivo()) {
            throw new RuntimeException("Usuario inactivo");
        }
        if (!usuarioAuthPort.validarPassword(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Credenciales invalidas");
        }
        String token = tokenProvider.generateToken(user.getEmail(), user.getTenantId());
        return new LoginResponse(token, user.getEmail(), user.getRol(), user.getTenantId());
    }
}
