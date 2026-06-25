package com.lunorion.labs.core.cita.infrastructure.adapters.out.rest;

import org.springframework.stereotype.Service;

@Service
public class NotificacionRestClient {

    public void enviarRecordatorioWhatsApp(String telefono, String mensaje) {
        // Stub para integracion futura con API de WhatsApp
    }

    public void enviarRecordatorioGmail(String email, String asunto, String cuerpo) {
        // Stub para integracion futura con Gmail API
    }
}
