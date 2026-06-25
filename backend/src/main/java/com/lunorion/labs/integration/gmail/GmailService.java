package com.lunorion.labs.integration.gmail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GmailService {

    private static final Logger log = LoggerFactory.getLogger(GmailService.class);

    public void enviarEmail(String to, String subject, String body) {
        log.info("Enviando email a: {}, asunto: {}", to, subject);
        log.info("Cuerpo: {}", body);
    }

    public void enviarConAdjunto(String to, String subject, String body, byte[] attachment, String filename) {
        log.info("Enviando email con adjunto a: {}, asunto: {}, archivo: {}", to, subject, filename);
        log.info("Cuerpo: {}, tamaño adjunto: {} bytes", body, attachment.length);
    }
}
