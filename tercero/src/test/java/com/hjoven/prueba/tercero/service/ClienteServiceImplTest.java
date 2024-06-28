package com.hjoven.prueba.tercero.service;

import com.hjoven.prueba.shared.entities.Cliente;
import com.hjoven.prueba.tercero.repository.ClienteRepository;
import com.hjoven.prueba.tercero.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteServiceImpl;

    @Test
    public void testSaveCliente() {


        Cliente cliente = new Cliente();
        cliente.setIdentificacion("1234567890");
        cliente.setNombresApellidos("Juan Pérez");
        cliente.setEdad(30);
        cliente.setGenero("M");
        cliente.setDireccion("Calle 123 #45-67");
        cliente.setTelefono("3001234567");
        cliente.setContrasenia("securepassword");
        cliente.setEstado(true);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente savedCliente = clienteServiceImpl.save(cliente);

        assertNotNull(savedCliente);
        assertEquals("securepassword", savedCliente.getContrasenia());
        assertEquals(true, savedCliente.getEstado());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testGetClienteById() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setContrasenia("securepassword");
        cliente.setEstado(true);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> retrievedCliente = Optional.ofNullable(clienteServiceImpl.findById(1L));

        assertTrue(retrievedCliente.isPresent());
        assertEquals("securepassword", retrievedCliente.get().getContrasenia());
        assertEquals(true, retrievedCliente.get().getEstado());
        verify(clienteRepository, times(1)).findById(1L);
    }
}