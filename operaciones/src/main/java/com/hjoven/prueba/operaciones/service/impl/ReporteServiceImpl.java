package com.hjoven.prueba.operaciones.service.impl;

import com.hjoven.prueba.operaciones.dto.EstadoCuentaDto;
import com.hjoven.prueba.operaciones.dto.MovimientoEstadoCuentaDto;
import com.hjoven.prueba.operaciones.repository.CuentaRepository;
import com.hjoven.prueba.operaciones.repository.MovimientoRepository;
import com.hjoven.prueba.operaciones.service.ReporteService;
import com.hjoven.prueba.shared.entities.Cuenta;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements ReporteService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public List<EstadoCuentaDto> estadoCuentaReport(String fechas, String identificacion) {
        List<EstadoCuentaDto> estadoCuentaDtoList = new ArrayList<>();

        List<Cuenta> cuentas  = this.cuentaRepository.findByClienteIdentificacion(identificacion);
        if (cuentas != null && !cuentas.isEmpty()) {
            cuentas.forEach(cuenta -> {
                Float saldo = 0F;
                String fechaInicioString = fechas.split(",")[0].trim();
                String fechaFinString = fechas.split(",")[1].trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaInicio = LocalDate.parse(fechaInicioString, formatter);
                LocalDate fechaFin = LocalDate.parse(fechaFinString, formatter);


                List<MovimientoEstadoCuentaDto> movimientos = this.movimientoRepository.findMovimientosByCuenta(cuenta.getId(), fechaInicio, fechaFin);

                if (movimientos != null && !movimientos.isEmpty()) {
                    Optional<MovimientoEstadoCuentaDto> ultimoMovimientoOptional = movimientos.stream().findFirst();
                    saldo = ultimoMovimientoOptional.get().getSaldo();
                } else {
                    saldo = cuenta.getSaldoInicial();
                }


                EstadoCuentaDto estadoCuentaDto = new EstadoCuentaDto();
                estadoCuentaDto.setNumeroCuenta(cuenta.getNumeroCuenta());
                estadoCuentaDto.setCliente(cuenta.getCliente().getNombresApellidos());
                estadoCuentaDto.setSaldo(saldo);
                estadoCuentaDto.setMovimientoEstadoCuentaDtos(movimientos);
                estadoCuentaDtoList.add(estadoCuentaDto);
            });
        }

        return estadoCuentaDtoList;
    }
}
