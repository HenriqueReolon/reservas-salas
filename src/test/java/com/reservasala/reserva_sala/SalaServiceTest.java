package com.reservasala.reserva_sala.service;

import com.reservasala.reserva_sala.model.Sala;
import com.reservasala.reserva_sala.repository.SalaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/** Classe de teste CRUD para o serviço de salas **/

class SalaServiceTest {

    @Mock
    private SalaRepository repository;

    @InjectMocks
    private SalaService service;

    public SalaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listar() {
        List<Sala> salas = Arrays.asList(
            new Sala(1L, "Sala A", 10),
            new Sala(2L, "Sala B", 20)
        );
        when(repository.findAll()).thenReturn(salas);

        List<Sala> result = service.listar();

        assertEquals(2, result.size());
        assertEquals("Sala A", result.get(0).getNome());
        assertEquals(20, result.get(1).getCapacidade());
        verify(repository, times(1)).findAll();
    }

    @Test
    void listarDeveRetornarListaVazia() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Sala> result = service.listar();

        assertTrue(result.isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    void salvar() {
        Sala sala = new Sala(1L, "Sala A", 10);
        when(repository.save(sala)).thenReturn(sala);

        Sala result = service.salvar(sala);

        assertNotNull(result);
        assertEquals("Sala A", result.getNome());
        assertEquals(10, result.getCapacidade());
        verify(repository, times(1)).save(sala);
    }

    @Test
    void salvarDeveAtualizarSala() {
        Sala sala = new Sala(1L, "Sala A", 10);
        Sala updatedSala = new Sala(1L, "Sala A", 15);
        when(repository.save(sala)).thenReturn(updatedSala);

        Sala result = service.salvar(sala);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(15, result.getCapacidade());
        verify(repository, times(1)).save(sala);
    }
}
