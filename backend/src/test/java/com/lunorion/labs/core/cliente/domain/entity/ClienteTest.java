package com.lunorion.labs.core.cliente.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Cliente Domain Entity")
class ClienteTest {

    private static final String TENANT_ID = "550e8400-e29b-41d4-a716-446655440000";
    private static final String DOC_TIPO = "DNI";
    private static final String DOC_NUMERO = "12345678";
    private static final String NOMBRES = "Juan";
    private static final String APELLIDOS = "Perez";
    private static final String RAZON_SOCIAL = "Juan Perez EIRL";
    private static final String DIRECCION = "Av. Principal 123";
    private static final String TELEFONO = "999888777";
    private static final String EMAIL = "juan@email.com";

    @Nested
    @DisplayName("create() factory")
    class Create {

        @Test
        @DisplayName("debe crear un cliente con todos los campos y activo=true")
        void shouldCreateClienteWithAllFields() {
            Cliente cliente = Cliente.create(TENANT_ID, DOC_TIPO, DOC_NUMERO, NOMBRES,
                    APELLIDOS, RAZON_SOCIAL, DIRECCION, TELEFONO, EMAIL);

            assertThat(cliente).isNotNull();
            assertThat(cliente.getId()).isNotNull();
            assertThat(cliente.getTenantId()).isEqualTo(TENANT_ID);
            assertThat(cliente.getTipoDocumento()).isEqualTo(DOC_TIPO);
            assertThat(cliente.getNumeroDocumento()).isEqualTo(DOC_NUMERO);
            assertThat(cliente.getNombres()).isEqualTo(NOMBRES);
            assertThat(cliente.getApellidos()).isEqualTo(APELLIDOS);
            assertThat(cliente.getRazonSocial()).isEqualTo(RAZON_SOCIAL);
            assertThat(cliente.getDireccion()).isEqualTo(DIRECCION);
            assertThat(cliente.getTelefono()).isEqualTo(TELEFONO);
            assertThat(cliente.getEmail()).isEqualTo(EMAIL);
            assertThat(cliente.isActivo()).isTrue();
            assertThat(cliente.getCreatedAt()).isNotNull();
        }
    }

    @Nested
    @DisplayName("desactivar()")
    class Desactivar {

        @Test
        @DisplayName("debe establecer activo=false y actualizar timestamp")
        void shouldSetActivoFalseAndUpdateTimestamp() {
            Cliente cliente = Cliente.create(TENANT_ID, DOC_TIPO, DOC_NUMERO, NOMBRES,
                    APELLIDOS, RAZON_SOCIAL, DIRECCION, TELEFONO, EMAIL);
            var beforeUpdate = cliente.getUpdatedAt();

            cliente.desactivar();

            assertThat(cliente.isActivo()).isFalse();
            assertThat(cliente.getUpdatedAt()).isNotNull();
            assertThat(cliente.getUpdatedAt()).isAfterOrEqualTo(beforeUpdate);
        }
    }

    @Nested
    @DisplayName("activar()")
    class Activar {

        @Test
        @DisplayName("debe establecer activo=true y actualizar timestamp")
        void shouldSetActivoTrueAndUpdateTimestamp() {
            Cliente cliente = Cliente.create(TENANT_ID, DOC_TIPO, DOC_NUMERO, NOMBRES,
                    APELLIDOS, RAZON_SOCIAL, DIRECCION, TELEFONO, EMAIL);
            cliente.desactivar();
            var beforeUpdate = cliente.getUpdatedAt();

            cliente.activar();

            assertThat(cliente.isActivo()).isTrue();
            assertThat(cliente.getUpdatedAt()).isAfterOrEqualTo(beforeUpdate);
        }
    }

    @Nested
    @DisplayName("actualizar()")
    class Actualizar {

        @Test
        @DisplayName("debe actualizar el timestamp sin modificar otros campos")
        void shouldUpdateTimestampOnly() {
            Cliente cliente = Cliente.create(TENANT_ID, DOC_TIPO, DOC_NUMERO, NOMBRES,
                    APELLIDOS, RAZON_SOCIAL, DIRECCION, TELEFONO, EMAIL);
            var nombreOriginal = cliente.getNombres();
            var beforeUpdate = cliente.getUpdatedAt();

            cliente.actualizar();

            assertThat(cliente.getNombres()).isEqualTo(nombreOriginal);
            assertThat(cliente.getUpdatedAt()).isAfterOrEqualTo(beforeUpdate);
        }
    }

    @Nested
    @DisplayName("Constructor con parametros")
    class Constructor {

        @Test
        @DisplayName("debe crear cliente con ID y campos principales, activo=true por defecto")
        void shouldCreateClienteWithIdAndMainFields() {
            UUID id = UUID.randomUUID();
            Cliente cliente = new Cliente(id, TENANT_ID, DOC_TIPO, DOC_NUMERO,
                    NOMBRES, APELLIDOS, RAZON_SOCIAL);

            assertThat(cliente.getId()).isEqualTo(id);
            assertThat(cliente.isActivo()).isTrue();
            assertThat(cliente.getCreatedAt()).isNotNull();
        }
    }
}
