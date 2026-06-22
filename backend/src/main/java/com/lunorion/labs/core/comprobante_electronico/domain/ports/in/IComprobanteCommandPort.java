package com.lunorion.labs.core.comprobante_electronico.domain.ports.in;

import com.lunorion.labs.core.comprobante_electronico.application.dto.in.CreateComprobanteRequest;
import com.lunorion.labs.core.comprobante_electronico.application.dto.out.ComprobanteResponse;

public interface IComprobanteCommandPort {
    ComprobanteResponse create(CreateComprobanteRequest request);
    void firmar(String id);
    void enviarSunat(String id);
    void aceptar(String id);
    void rechazar(String id, String error);
}
