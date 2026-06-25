package com.lunorion.labs.core.cliente.application.dto.in;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para crear o actualizar un cliente")
public class CreateClienteRequest {
    @Schema(description = "ID del tenant", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String tenantId;
    @Schema(description = "Tipo de documento (DNI, RUC, etc.)", example = "DNI")
    private String tipoDocumento;
    @Schema(description = "Número de documento", example = "12345678")
    private String numeroDocumento;
    @Schema(description = "Nombres", example = "Juan")
    private String nombres;
    @Schema(description = "Apellidos", example = "Pérez")
    private String apellidos;
    @Schema(description = "Razón social (para empresas)", example = "Empresa SAC")
    private String razonSocial;
    @Schema(description = "Dirección", example = "Av. Principal 123")
    private String direccion;
    @Schema(description = "Teléfono", example = "999888777")
    private String telefono;
    @Schema(description = "Email", example = "juan@email.com")
    private String email;

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
