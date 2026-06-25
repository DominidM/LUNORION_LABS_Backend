package com.lunorion.labs.core.checkin.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "checkin_foto")
public class CheckinFotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "checkin_id", nullable = false)
    private UUID checkinId;

    @Column(length = 20)
    private String tipo;

    @Column(length = 20)
    private String angulo;

    @Column(columnDefinition = "TEXT")
    private String url;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public CheckinFotoEntity() {}

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getCheckinId() { return checkinId; }
    public void setCheckinId(UUID checkinId) { this.checkinId = checkinId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getAngulo() { return angulo; }
    public void setAngulo(String angulo) { this.angulo = angulo; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
