package com.lunorion.labs.core.cliente.application.service.command;

import com.lunorion.labs.core.cliente.application.dto.in.CreateClienteRequest;
import com.lunorion.labs.core.cliente.application.dto.out.ClienteResponse;
import com.lunorion.labs.core.cliente.application.mapper.ClienteMapper;
import com.lunorion.labs.core.cliente.domain.entity.Cliente;
import com.lunorion.labs.core.cliente.domain.ports.out.IClienteRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ClienteCommandService")
class ClienteCommandServiceTest {

    @Mock
    private IClienteRepositoryPort repository;

    @Mock
    private ClienteMapper mapper;

    @InjectMocks
    private ClienteCommandService service;

    private CreateClienteRequest request;
    private Cliente cliente;
    private ClienteResponse response;

    @BeforeEach
    void setUp() {
        request = new CreateClienteRequest();
        request.setTenantId("550e8400-e29b-41d4-a716-446655440000");
        request.setTipoDocumento("DNI");
        request.setNumeroDocumento("12345678");
        request.setNombres("Juan");
        request.setApellidos("Perez");
        request.setRazonSocial("Juan Perez EIRL");
        request.setDireccion("Av. Principal 123");
        request.setTelefono("999888777");
        request.setEmail("juan@email.com");

        cliente = Cliente.create(
                request.getTenantId(), request.getTipoDocumento(), request.getNumeroDocumento(),
                request.getNombres(), request.getApellidos(), request.getRazonSocial(),
                request.getDireccion(), request.getTelefono(), request.getEmail());

        response = new ClienteResponse();
        response.setId(cliente.getId().toString());
        response.setNumeroDocumento(request.getNumeroDocumento());
        response.setNombres(request.getNombres());
        response.setActivo(true);
    }

    @Nested
    @DisplayName("create()")
    class Create {

        @Test
        @DisplayName("debe crear cliente exitosamente cuando no existe documento duplicado")
        void shouldCreateClienteSuccessfully() {
            when(repository.findByNumeroDocumento(request.getNumeroDocumento()))
                    .thenReturn(Optional.empty());
            when(mapper.toDomain(request)).thenReturn(cliente);
            when(repository.save(cliente)).thenReturn(cliente);
            when(mapper.toResponse(cliente)).thenReturn(response);

            ClienteResponse result = service.create(request);

            assertThat(result).isNotNull();
            assertThat(result.getNumeroDocumento()).isEqualTo(request.getNumeroDocumento());
            assertThat(result.getNombres()).isEqualTo(request.getNombres());
            verify(repository).findByNumeroDocumento(request.getNumeroDocumento());
            verify(mapper).toDomain(request);
            verify(repository).save(cliente);
            verify(mapper).toResponse(cliente);
        }

        @Test
        @DisplayName("debe lanzar IllegalArgumentException cuando el documento ya existe")
        void shouldThrowWhenDocumentoExists() {
            when(repository.findByNumeroDocumento(request.getNumeroDocumento()))
                    .thenReturn(Optional.of(cliente));

            assertThatThrownBy(() -> service.create(request))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("ya está registrado");

            verify(repository, never()).save(any());
            verify(mapper, never()).toDomain(any());
        }
    }

    @Nested
    @DisplayName("update()")
    class Update {

        @Test
        @DisplayName("debe actualizar cliente exitosamente")
        void shouldUpdateClienteSuccessfully() {
            String id = cliente.getId().toString();
            when(repository.findByNumeroDocumento(request.getNumeroDocumento()))
                    .thenReturn(Optional.of(cliente));
            when(repository.findById(id)).thenReturn(Optional.of(cliente));
            when(repository.save(cliente)).thenReturn(cliente);
            when(mapper.toResponse(cliente)).thenReturn(response);

            ClienteResponse result = service.update(id, request);

            assertThat(result).isNotNull();
            verify(mapper).updateDomain(cliente, request);
            verify(repository).save(cliente);
        }

        @Test
        @DisplayName("debe lanzar IllegalArgumentException cuando el cliente no existe")
        void shouldThrowWhenClienteNotFound() {
            String id = UUID.randomUUID().toString();
            when(repository.findByNumeroDocumento(request.getNumeroDocumento()))
                    .thenReturn(Optional.empty());
            when(repository.findById(id)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> service.update(id, request))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Cliente no encontrado");

            verify(repository, never()).save(any());
        }

        @Test
        @DisplayName("debe lanzar IllegalArgumentException cuando documento pertenece a otro cliente")
        void shouldThrowWhenDocumentoBelongsToOtherCliente() {
            String id = UUID.randomUUID().toString();
            Cliente otroCliente = Cliente.create(
                    request.getTenantId(), request.getTipoDocumento(), request.getNumeroDocumento(),
                    "Otro", "Cliente", "Otro EIRL",
                    "Dir", "000", "otro@email.com");

            when(repository.findByNumeroDocumento(request.getNumeroDocumento()))
                    .thenReturn(Optional.of(otroCliente));

            assertThatThrownBy(() -> service.update(id, request))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("ya está registrado por otro cliente");

            verify(repository, never()).save(any());
        }
    }

    @Nested
    @DisplayName("desactivar()")
    class Desactivar {

        @Test
        @DisplayName("debe desactivar cliente existente")
        void shouldDesactivarExistingCliente() {
            String id = cliente.getId().toString();
            when(repository.findById(id)).thenReturn(Optional.of(cliente));

            service.desactivar(id);

            assertThat(cliente.isActivo()).isFalse();
            verify(repository).save(cliente);
        }

        @Test
        @DisplayName("no debe fallar si el cliente no existe")
        void shouldNotFailWhenClienteNotFound() {
            String id = UUID.randomUUID().toString();
            when(repository.findById(id)).thenReturn(Optional.empty());

            service.desactivar(id);

            verify(repository, never()).save(any());
        }
    }

    @Nested
    @DisplayName("activar()")
    class Activar {

        @Test
        @DisplayName("debe activar cliente existente")
        void shouldActivarExistingCliente() {
            String id = cliente.getId().toString();
            cliente.desactivar();
            when(repository.findById(id)).thenReturn(Optional.of(cliente));

            service.activar(id);

            assertThat(cliente.isActivo()).isTrue();
            verify(repository).save(cliente);
        }
    }
}
