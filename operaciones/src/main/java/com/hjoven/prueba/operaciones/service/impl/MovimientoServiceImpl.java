package com.hjoven.prueba.operaciones.service.impl;

import com.hjoven.prueba.operaciones.feignclient.ClienteFeign;
import com.hjoven.prueba.operaciones.repository.MovimientoRepository;
import com.hjoven.prueba.operaciones.service.CuentaService;
import com.hjoven.prueba.operaciones.service.MovimientoService;
import com.hjoven.prueba.shared.entities.Cuenta;
import com.hjoven.prueba.shared.entities.Movimiento;
import com.hjoven.prueba.shared.utils.GlobalConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository repository;

    @Autowired
    private CuentaService cuentaService;

    @Override
    public Page<Movimiento> getDatatable(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public List<Movimiento> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Movimiento findById(Long id) throws Exception {
        Optional<Movimiento> movimientoOptional = this.repository.findById(id);
        if (movimientoOptional.isEmpty()) throw new Exception("Registro no encontrado");
        return movimientoOptional.get();
    }

    @Transactional
    @Override
    public Movimiento save(Movimiento movimiento) throws Exception {
        float ultimoSaldoMovimiento = 0;
        Cuenta cuenta = cuentaService.findById(movimiento.getCuenta().getId());

        Optional<Movimiento> ultimoMovimiento = this.repository.findTopByCuentaIdOrderByFechaDesc(cuenta.getId());
        if (ultimoMovimiento.isPresent()) {
            ultimoSaldoMovimiento = ultimoMovimiento.get().getSaldo();
        } else {
            ultimoSaldoMovimiento = cuenta.getSaldoInicial();
        }

        float saldo = ultimoSaldoMovimiento + movimiento.getValor();

        if (saldo < 0) {
            throw new Exception("Saldo no disponible");
        }

        movimiento.setSaldo(saldo);
        movimiento.setFecha(LocalDate.now());
        return this.repository.save(movimiento);
    }

    @Override
    public void update(Long id, Movimiento movimiento) throws Exception {
        Movimiento movimientoDatabase = this.findById(id);
        BeanUtils.copyProperties(movimiento, movimientoDatabase, GlobalConstants.EXCLUDED_FIELDS);
        this.repository.save(movimientoDatabase);
    }

    @Override
    public void delete(Long id) throws Exception {
        this.repository.deleteById(id);
    }
}
