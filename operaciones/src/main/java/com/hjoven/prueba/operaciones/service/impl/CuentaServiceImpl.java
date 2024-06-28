package com.hjoven.prueba.operaciones.service.impl;

import com.hjoven.prueba.operaciones.repository.CuentaRepository;
import com.hjoven.prueba.operaciones.service.CuentaService;
import com.hjoven.prueba.shared.entities.Cuenta;
import com.hjoven.prueba.shared.utils.GlobalConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository repository;

    @Override
    public Page<Cuenta> getDatatable(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public List<Cuenta> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Cuenta findById(Long id) throws Exception {
        Optional<Cuenta> cuentaOptional = this.repository.findById(id);
        if (cuentaOptional.isEmpty()) throw new Exception("Registro no encontrado");
        return cuentaOptional.get();
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        return this.repository.save(cuenta);
    }

    @Override
    public void update(Long id, Cuenta cuenta) throws Exception {
        Cuenta cuentaDatabase = this.findById(id);
        BeanUtils.copyProperties(cuenta, cuentaDatabase, GlobalConstants.EXCLUDED_FIELDS);
        this.repository.save(cuentaDatabase);
    }

    @Override
    public void delete(Long id) throws Exception {
        this.repository.deleteById(id);
    }
}
