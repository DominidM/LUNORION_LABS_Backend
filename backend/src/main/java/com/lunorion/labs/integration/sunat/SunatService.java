package com.lunorion.labs.integration.sunat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SunatService {

    private static final Logger log = LoggerFactory.getLogger(SunatService.class);
    private final RestTemplate restTemplate;

    @Value("${sunat.api.url:https://api.apisperu.com/v1/sunat}")
    private String apiUrl;

    @Value("${sunat.api.token:}")
    private String apiToken;

    public SunatService() {
        this.restTemplate = new RestTemplate();
    }

    public SunatResponse consultarRuc(String ruc) {
        try {
            String url = apiUrl + "/" + ruc + "?token=" + apiToken;
            return restTemplate.getForObject(url, SunatResponse.class);
        } catch (Exception e) {
            log.error("Error consultando RUC {}: {}", ruc, e.getMessage());
            return null;
        }
    }
}
