package com.lunorion.labs.core.cliente.infrastructure.adapters.out.persistence.repository;

import com.lunorion.labs.core.cliente.domain.entity.Cliente;
import com.lunorion.labs.core.cliente.infrastructure.adapters.out.persistence.entity.ClienteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ClienteRepositoryAdapter Integration")
@DataJpaTest
@Import({ClienteRepositoryAdapter.class,
        com.lunorion.labs.core.cliente.infrastructure.adapters.out.persistence.mapper.ClienteEntityMapper.class})
class ClienteRepositoryAdapterTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClienteRepositoryAdapter adapter;

    private UUID tenantId;
    private ClienteEntity entity;

    @BeforeEach
    void setUp() {
        tenantId = UUID.randomUUID();
        entity = new ClienteEntity();
        entity.setTenantId(tenantId);
        entity.setTipoDocumento("DNI");
        entity.setNumeroDocumento("87654321");
        entity.setNombres("Maria");
        entity.setApellidos("Lopez");
        entity.setRazonSocial("Maria Lopez EIRL");
        entity.setDireccion("Jr. Las Flores 456");
        entity.setTelefono("987654321");
        entity.setEmail("maria@email.com");
        entity.setActivo(true);
        entity = entityManager.persistAndFlush(entity);
    }

    @Nested
    @DisplayName("save()")
    class Save {

        @Test
        @DisplayName("debe persistir un cliente nuevo")
        void shouldPersistNewCliente() {
            Cliente domain = Cliente.create(
                    tenantId.toString(), "RUC", "20123456789",
                    "Empresa", "", "Empresa SAC",
                    "Av. Central 789", "999111222", "empresa@email.com");

            Cliente saved = adapter.save(domain);

            assertThat(saved).isNotNull();
            assertThat(saved.getId()).isNotNull();
            assertThat(saved.getNumeroDocumento()).isEqualTo("20123456789");
            assertThat(saved.isActivo()).isTrue();
        }

        @Test
        @DisplayName("debe actualizar un cliente existente")
        void shouldUpdateExistingCliente() {
            Optional<Cliente> found = adapter.findById(entity.getId().toString());
            assertThat(found).isPresent();
            Cliente domain = found.get();
            domain.setNombres("Maria Updated");

            Cliente updated = adapter.save(domain);

            assertThat(updated.getNombres()).isEqualTo("Maria Updated");
            assertThat(updated.getId()).isEqualTo(entity.getId());
        }
    }

    @Nested
    @DisplayName("findById()")
    class FindById {

        @Test
        @DisplayName("debe encontrar cliente por ID")
        void shouldFindById() {
            Optional<Cliente> found = adapter.findById(entity.getId().toString());

            assertThat(found).isPresent();
            assertThat(found.get().getNumeroDocumento()).isEqualTo("87654321");
            assertThat(found.get().getNombres()).isEqualTo("Maria");
        }

        @Test
        @DisplayName("debe retornar empty cuando no existe")
        void shouldReturnEmptyWhenNotFound() {
            Optional<Cliente> found = adapter.findById(UUID.randomUUID().toString());

            assertThat(found).isEmpty();
        }
    }

    @Nested
    @DisplayName("findByNumeroDocumento()")
    class FindByNumeroDocumento {

        @Test
        @DisplayName("debe encontrar cliente por numero de documento")
        void shouldFindByDocumento() {
            Optional<Cliente> found = adapter.findByNumeroDocumento("87654321");

            assertThat(found).isPresent();
            assertThat(found.get().getEmail()).isEqualTo("maria@email.com");
        }

        @Test
        @DisplayName("debe retornar empty cuando no existe")
        void shouldReturnEmptyWhenDocumentoNotFound() {
            Optional<Cliente> found = adapter.findByNumeroDocumento("99999999");

            assertThat(found).isEmpty();
        }
    }

    @Nested
    @DisplayName("findByTenantId()")
    class FindByTenantId {

        @Test
        @DisplayName("debe listar clientes por tenant")
        void shouldFindByTenantId() {
            var clientes = adapter.findByTenantId(tenantId.toString());

            assertThat(clientes).hasSize(1);
            assertThat(clientes.get(0).getTenantId()).isEqualTo(tenantId.toString());
        }
    }

    @Nested
    @DisplayName("findAll()")
    class FindAll {

        @Test
        @DisplayName("debe listar todos los clientes")
        void shouldFindAll() {
            ClienteEntity otro = new ClienteEntity();
            otro.setTenantId(tenantId);
            otro.setTipoDocumento("RUC");
            otro.setNumeroDocumento("20987654321");
            otro.setNombres("Otro");
            otro.setApellidos("Cliente");
            otro.setRazonSocial("Otro EIRL");
            otro.setDireccion("Calle 123");
            otro.setTelefono("123456789");
            otro.setEmail("otro@email.com");
            otro.setActivo(true);
            entityManager.persistAndFlush(otro);

            var clientes = adapter.findAll();

            assertThat(clientes).hasSize(2);
        }
    }
}
