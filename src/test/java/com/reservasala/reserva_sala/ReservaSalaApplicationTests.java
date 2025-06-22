package com.reservasala.reserva_sala;

import com.reservasala.reserva_sala.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

/** Classe de teste do Springboot, nao interfere com a classe do Maven **/

@SpringBootTest
@Testcontainers
class ReservaSalaApplicationTests {

	@Container // Banco de dados temporário para testes
	public static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
		.withDatabaseName("reservasdb")
		.withUsername("admin")
		.withPassword("admin");

	@Autowired
	private UsuarioService usuarioService;

	@DynamicPropertySource
	static void postgresqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
	}

	@Test
	void contextLoads() {
		// Verifies that the application context loads successfully
	}

	@Test
	void usuarioServiceBeanIsLoaded() {
		// Verifies that the UsuarioService bean is loaded in the application context
		assertThat(usuarioService).isNotNull();
	}
}