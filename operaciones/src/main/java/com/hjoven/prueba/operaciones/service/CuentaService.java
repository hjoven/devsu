package com.hjoven.prueba.operaciones.service;

import com.hjoven.prueba.shared.entities.Cliente;
import com.hjoven.prueba.shared.entities.Cuenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CuentaService {
    Page<Cuenta> getDatatable(Pageable pageable);
    List<Cuenta> getAll();
    Cuenta findById(Long id) throws Exception;
    Cuenta save(Cuenta cuenta);
    void update(Long id, Cuenta cuenta) throws Exception;
    void delete(Long id) throws Exception;
}
