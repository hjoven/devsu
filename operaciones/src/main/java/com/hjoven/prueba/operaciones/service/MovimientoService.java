package com.hjoven.prueba.operaciones.service;

import com.hjoven.prueba.shared.entities.Movimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovimientoService {
    Page<Movimiento> getDatatable(Pageable pageable);
    List<Movimiento> getAll();
    Movimiento findById(Long id) throws Exception;
    Movimiento save(Movimiento movimiento) throws Exception;
    void update(Long id, Movimiento movimiento) throws Exception;
    void delete(Long id) throws Exception;
}
