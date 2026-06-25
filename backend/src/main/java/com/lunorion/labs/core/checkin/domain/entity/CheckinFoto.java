package com.lunorion.labs.core.checkin.domain.entity;

import java.util.UUID;

public class CheckinFoto {
    private UUID id;
    private String checkinId;
    private TipoFoto tipo;
    private AnguloFoto angulo;
    private String url;

    public CheckinFoto() {}

    public CheckinFoto(UUID id, String checkinId, TipoFoto tipo, AnguloFoto angulo, String url) {
        this.id = id;
        this.checkinId = checkinId;
        this.tipo = tipo;
        this.angulo = angulo;
        this.url = url;
    }

    public static CheckinFoto create(String checkinId, TipoFoto tipo, AnguloFoto angulo, String url) {
        return new CheckinFoto(UUID.randomUUID(), checkinId, tipo, angulo, url);
    }

    public UUID getId() { return id; }
    public String getCheckinId() { return checkinId; }
    public TipoFoto getTipo() { return tipo; }
    public AnguloFoto getAngulo() { return angulo; }
    public String getUrl() { return url; }
}
