package com.lunorion.labs.core.cliente.infrastructure.adapters.in.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunorion.labs.core.cliente.application.dto.in.CreateClienteRequest;
import com.lunorion.labs.core.cliente.application.dto.out.ClienteResponse;
import com.lunorion.labs.core.cliente.domain.ports.in.IClienteCommandPort;
import com.lunorion.labs.core.cliente.domain.ports.in.IClienteQueryPort;
import com.lunorion.labs.shared.infrastructure.security.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = ClienteController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
@DisplayName("ClienteController")
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IClienteCommandPort commandService;

    @MockBean
    private IClienteQueryPort queryService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private static final String BASE_URL = "/api/clientes";

    private CreateClienteRequest buildRequest() {
        CreateClienteRequest request = new CreateClienteRequest();
        request.setTenantId("550e8400-e29b-41d4-a716-446655440000");
        request.setTipoDocumento("DNI");
        request.setNumeroDocumento("12345678");
        request.setNombres("Juan");
        request.setApellidos("Perez");
        request.setRazonSocial("Juan Perez EIRL");
        request.setDireccion("Av. Principal 123");
        request.setTelefono("999888777");
        request.setEmail("juan@email.com");
        return request;
    }

    private ClienteResponse buildResponse(String id) {
        ClienteResponse response = new ClienteResponse();
        response.setId(id);
        response.setTipoDocumento("DNI");
        response.setNumeroDocumento("12345678");
        response.setNombres("Juan");
        response.setApellidos("Perez");
        response.setRazonSocial("Juan Perez EIRL");
        response.setDireccion("Av. Principal 123");
        response.setTelefono("999888777");
        response.setEmail("juan@email.com");
        response.setActivo(true);
        return response;
    }

    @Nested
    @DisplayName("POST /api/clientes")
    class CreateCliente {

        @Test
        @DisplayName("debe crear cliente y retornar 201")
        void shouldCreateCliente() throws Exception {
            CreateClienteRequest request = buildRequest();
            ClienteResponse response = buildResponse("123e4567-e89b-12d3-a456-426614174000");

            when(commandService.create(any(CreateClienteRequest.class))).thenReturn(response);

            mockMvc.perform(post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value("123e4567-e89b-12d3-a456-426614174000"))
                    .andExpect(jsonPath("$.nombres").value("Juan"))
                    .andExpect(jsonPath("$.activo").value(true));

            verify(commandService).create(any(CreateClienteRequest.class));
        }
    }

    @Nested
    @DisplayName("PUT /api/clientes/{id}")
    class UpdateCliente {

        @Test
        @DisplayName("debe actualizar cliente y retornar 200")
        void shouldUpdateCliente() throws Exception {
            String id = "123e4567-e89b-12d3-a456-426614174000";
            CreateClienteRequest request = buildRequest();
            ClienteResponse response = buildResponse(id);
            response.setNombres("Juan Updated");

            when(commandService.update(eq(id), any(CreateClienteRequest.class)))
                    .thenReturn(response);

            mockMvc.perform(put(BASE_URL + "/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nombres").value("Juan Updated"));

            verify(commandService).update(eq(id), any(CreateClienteRequest.class));
        }
    }

    @Nested
    @DisplayName("GET /api/clientes/{id}")
    class FindById {

        @Test
        @DisplayName("debe retornar 200 cuando el cliente existe")
        void shouldReturnCliente() throws Exception {
            String id = "123e4567-e89b-12d3-a456-426614174000";
            ClienteResponse response = buildResponse(id);

            when(queryService.findById(id)).thenReturn(Optional.of(response));

            mockMvc.perform(get(BASE_URL + "/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id))
                    .andExpect(jsonPath("$.nombres").value("Juan"));
        }

        @Test
        @DisplayName("debe retornar 404 cuando el cliente no existe")
        void shouldReturn404WhenNotFound() throws Exception {
            when(queryService.findById("nonexistent")).thenReturn(Optional.empty());

            mockMvc.perform(get(BASE_URL + "/{id}", "nonexistent"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("GET /api/clientes")
    class FindAll {

        @Test
        @DisplayName("debe retornar lista de clientes")
        void shouldReturnAllClientes() throws Exception {
            ClienteResponse r1 = buildResponse("id-1");
            ClienteResponse r2 = buildResponse("id-2");

            when(queryService.findAll()).thenReturn(List.of(r1, r2));

            mockMvc.perform(get(BASE_URL))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(2));
        }
    }

    @Nested
    @DisplayName("POST /api/clientes/{id}/desactivar")
    class Desactivar {

        @Test
        @DisplayName("debe desactivar cliente y retornar 200")
        void shouldDesactivarCliente() throws Exception {
            String id = "123e4567-e89b-12d3-a456-426614174000";
            doNothing().when(commandService).desactivar(id);

            mockMvc.perform(post(BASE_URL + "/{id}/desactivar", id))
                    .andExpect(status().isOk());

            verify(commandService).desactivar(id);
        }
    }

    @Nested
    @DisplayName("POST /api/clientes/{id}/activar")
    class Activar {

        @Test
        @DisplayName("debe activar cliente y retornar 200")
        void shouldActivarCliente() throws Exception {
            String id = "123e4567-e89b-12d3-a456-426614174000";
            doNothing().when(commandService).activar(id);

            mockMvc.perform(post(BASE_URL + "/{id}/activar", id))
                    .andExpect(status().isOk());

            verify(commandService).activar(id);
        }
    }

    @Nested
    @DisplayName("GET /api/clientes/{id}/historial-trabajos")
    class WorkHistory {

        @Test
        @DisplayName("debe retornar historial de trabajos")
        void shouldReturnWorkHistory() throws Exception {
            String id = "123e4567-e89b-12d3-a456-426614174000";
            when(queryService.workHistory(id)).thenReturn(List.of());

            mockMvc.perform(get(BASE_URL + "/{id}/historial-trabajos", id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(0));
        }
    }
}
