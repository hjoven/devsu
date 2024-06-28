package com.hjoven.prueba.operaciones.service;

import com.hjoven.prueba.operaciones.repository.CuentaRepository;
import com.hjoven.prueba.operaciones.repository.MovimientoRepository;
import com.hjoven.prueba.operaciones.service.impl.MovimientoServiceImpl;
import com.hjoven.prueba.shared.entities.Cliente;
import com.hjoven.prueba.shared.entities.Cuenta;
import com.hjoven.prueba.shared.entities.Movimiento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MovimientoServiceImplIntegrationTest {

    @Autowired
    private MovimientoServiceImpl movimientoService;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Test
    public void testRegistrarMovimiento() throws Exception {
        // Limpiar la base de datos
        movimientoRepository.deleteAll();
        cuentaRepository.deleteAll();

        // Crear y guardar un nuevo cliente
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setContrasenia("securepassword");
        cliente.setEstado(true);



        // Crear y guardar una nueva cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(cliente);
        cuenta.setNumeroCuenta("123456");
        cuenta.setSaldoInicial(1000.0F);
        cuenta.setTipoCuenta(1);
        cuenta.setEstado(true);
        Cuenta savedCuenta = cuentaRepository.save(cuenta);

        // Registrar un nuevo movimiento
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(savedCuenta);
        movimiento.setTipoMovimiento(1);
        movimiento.setValor(500.0F);
        movimiento.setFecha(LocalDate.now());
        Movimiento movimientoDb = movimientoService.save(movimiento);

        assertNotNull(movimientoDb);
        assertEquals(500.0F, movimientoDb.getValor());
        assertNotNull(movimientoDb.getFecha());

        // Verificar el último movimiento
        Optional<Movimiento> ultimoMovimiento = movimientoRepository.findTopByCuentaIdOrderByFechaDesc(savedCuenta.getId());
        assertTrue(ultimoMovimiento.isPresent());
        assertEquals(1500.0F, ultimoMovimiento.get().getSaldo());


    }
}
