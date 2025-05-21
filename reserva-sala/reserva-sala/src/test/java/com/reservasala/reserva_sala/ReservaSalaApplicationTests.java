/** Arquivo de teste do Springboot, nao interfere com a classe do Maven **/
package com.reservasala.reserva_sala;

import com.reservasala.reserva_sala.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReservaSalaApplicationTests {

	@Autowired
	private UsuarioService usuarioService;

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