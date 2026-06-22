package com.lunorion.labs.integration.reniec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReniecService {

    private static final Logger log = LoggerFactory.getLogger(ReniecService.class);
    private final RestTemplate restTemplate;

    @Value("${reniec.apisperu.url:https://api.apisperu.com/v1/persona}")
    private String apisperuUrl;

    @Value("${reniec.apisperu.token:}")
    private String apisperuToken;

    @Value("${reniec.apipe.url:https://api.apis.pe/v1/dni}")
    private String apipeUrl;

    @Value("${reniec.apipe.token:}")
    private String apipeToken;

    public ReniecService() {
        this.restTemplate = new RestTemplate();
    }

    public ReniecResponse consultar(String numero) {
        ReniecResponse response = consultarApisperu(numero);
        if (response == null) {
            response = consultarApipe(numero);
        }
        return response;
    }

    private ReniecResponse consultarApisperu(String numero) {
        try {
            String url = apisperuUrl + "/" + numero + "?token=" + apisperuToken;
            return restTemplate.getForObject(url, ReniecResponse.class);
        } catch (Exception e) {
            log.warn("apisperu falló para DNI {}: {}", numero, e.getMessage());
            return null;
        }
    }

    private ReniecResponse consultarApipe(String numero) {
        try {
            String url = apipeUrl + "/" + numero + "?apiKey=" + apipeToken;
            return restTemplate.getForObject(url, ReniecResponse.class);
        } catch (Exception e) {
            log.warn("api.apis.pe falló para DNI {}: {}", numero, e.getMessage());
            return null;
        }
    }
}
