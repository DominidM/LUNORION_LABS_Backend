package com.lunorion.labs.core.comprobante_electronico.domain.ports.in;

import com.lunorion.labs.core.comprobante_electronico.application.dto.in.*;
import com.lunorion.labs.core.comprobante_electronico.application.dto.out.ComprobanteResponse;
import com.lunorion.labs.core.comprobante_electronico.application.dto.out.ResumenDiarioResponse;

import java.util.UUID;

public interface IComprobanteCommandPort {
    ComprobanteResponse create(CreateComprobanteRequest request);
    ComprobanteResponse emitirBoleta(EmitirBoletaRequest request);
    ComprobanteResponse emitirNotaCredito(NotaCreditoRequest request);
    ComprobanteResponse emitirNotaDebito(NotaDebitoRequest request);
    void firmar(UUID id);
    void enviarSunat(UUID id);
    void aceptar(UUID id);
    void rechazar(UUID id, String error);
    void reenviar(UUID id);
    ResumenDiarioResponse generarResumenDiario(CreateResumenDiarioRequest request);
}
