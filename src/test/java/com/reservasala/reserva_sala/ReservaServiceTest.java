package com.reservasala.reserva_sala.service;

import com.reservasala.reserva_sala.model.Reserva;
import com.reservasala.reserva_sala.model.Sala;
import com.reservasala.reserva_sala.model.Usuario;
import com.reservasala.reserva_sala.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/** Classe de teste para o serviço de reservas **/

class ReservaServiceTest {

    @Mock
    private ReservaRepository repository;

    @InjectMocks
    private ReservaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarDeveRetornarListaDeReservas() {
        Sala sala = new Sala(1L, "Sala Teste", 5);
        Usuario usuario = new Usuario(1L, "User", "user@test.com", "pass", "123456", "Rua", "1", "Cidade", "00000-000", "12345678901", null, null);
        Reserva r1 = new Reserva(1L, LocalDateTime.of(2025, 6, 22, 10, 0), sala, usuario);
        Reserva r2 = new Reserva(2L, LocalDateTime.of(2025, 6, 22, 11, 0), sala, usuario);
        List<Reserva> reservas = Arrays.asList(r1, r2);
        when(repository.findAll()).thenReturn(reservas);

        List<Reserva> result = service.listar();

        assertEquals(2, result.size());
        assertEquals(LocalDateTime.of(2025, 6, 22, 10, 0), result.get(0).getDataHora());
        verify(repository, times(1)).findAll();
    }

    @Test
    void salvarDevePersistirReserva() {
        Sala sala = new Sala(1L, "Sala Teste", 5);
        Usuario usuario = new Usuario(1L, "User", "user@test.com", "pass", "123456", "Rua", "1", "Cidade", "00000-000", "12345678901", null, null);
        Reserva reserva = new Reserva(null, LocalDateTime.now(), sala, usuario);
        Reserva saved = new Reserva(1L, reserva.getDataHora(), sala, usuario);
        when(repository.save(reserva)).thenReturn(saved);

        Reserva result = service.salvar(reserva);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(sala, result.getSala());
        verify(repository, times(1)).save(reserva);
    }

    @Test
    void listarDeveRetornarListaVazia() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Reserva> result = service.listar();

        assertTrue(result.isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    void salvarDeveAtualizarReserva() {
        Sala sala = new Sala(1L, "Sala Teste", 5);
        Usuario usuario = new Usuario(1L, "User", "user@test.com", "pass", "123456", "Rua", "1", "Cidade", "00000-000", "12345678901", null, null);
        LocalDateTime dt = LocalDateTime.of(2025, 6, 22, 9, 0);
        Reserva original = new Reserva(1L, dt, sala, usuario);
        Reserva updated = new Reserva(1L, LocalDateTime.of(2025, 6, 22, 10, 0), sala, usuario);
        when(repository.save(original)).thenReturn(updated);

        Reserva result = service.salvar(original);

        assertEquals(updated.getDataHora(), result.getDataHora());
        verify(repository, times(1)).save(original);
    }
}
