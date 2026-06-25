package com.lunorion.labs.core.comprobante_electronico.infrastructure.adapters.out.soap;

import org.springframework.stereotype.Service;

@Service
public class SunatSoapClient {

    public String enviarComprobante(String xmlFirmado) {
        return "<cdr>...</cdr>";
    }

    public String consultarEstado(String ruc, String tipo, String serie, int numero) {
        return "ACEPTADO";
    }
}
