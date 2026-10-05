package com.lunorion.labs.core.usuario.application.dto.in;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para actualizar un usuario/empleado")
public class UpdateUsuarioRequest {
    @Schema(description = "Nombres", example = "Juan")
    private String nombres;
    @Schema(description = "Apellidos", example = "Pérez")
    private String apellidos;
    @Schema(description = "DNI", example = "12345678")
    private String dni;
    @Schema(description = "Teléfono", example = "999888777")
    private String telefono;
    @Schema(description = "Email", example = "juan@email.com")
    private String email;
    @Schema(description = "Rol de acceso", allowableValues = {"SUPER_ADMIN", "ADMIN", "PUBLIC"}, example = "PUBLIC")
    private String rol;
    @Schema(description = "Nueva contraseña (opcional)", example = "nuevaClave123")
    private String password;

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
