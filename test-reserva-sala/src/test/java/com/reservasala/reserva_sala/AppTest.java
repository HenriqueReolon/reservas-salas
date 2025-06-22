package com.reservasala.reserva_sala;
import com.reservasala.reserva_sala.model.Usuario;
import com.reservasala.reserva_sala.repository.UsuarioRepository;
import com.reservasala.reserva_sala.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    public UsuarioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listar() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(1L, "John Doe", "john@example.com", "1234", "123456789", "Rua A", "123", "Cidade", "12345-678", "12345678901", null, null));
        when(repository.findAll()).thenReturn(usuarios);

        List<Usuario> result = service.listar();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNome());
        verify(repository, times(1)).findAll();
    }

    @Test
    void salvar() {
        Usuario usuario = new Usuario(1L, "John Doe", "john@example.com", "1234", "123456789", "Rua A", "123", "Cidade", "12345-678", "12345678901", null, null);
        when(repository.save(usuario)).thenReturn(usuario);

        Usuario result = service.salvar(usuario);

        assertNotNull(result);
        assertEquals("John Doe", result.getNome());
        verify(repository, times(1)).save(usuario);
    }

    @Test
    void buscarPorEmail() {
        Usuario usuario = new Usuario(1L, "John Doe", "john@example.com", "1234", "123456789", "Rua A", "123", "Cidade", "12345-678", "12345678901", null, null);
        when(repository.findByEmail("john@example.com")).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = service.buscarPorEmail("john@example.com");

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getNome());
        verify(repository, times(1)).findByEmail("john@example.com");
    }
}