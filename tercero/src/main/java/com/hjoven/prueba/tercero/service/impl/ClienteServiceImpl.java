package com.hjoven.prueba.tercero.service.impl;

import com.hjoven.prueba.shared.entities.Cliente;
import com.hjoven.prueba.shared.utils.GlobalConstants;
import com.hjoven.prueba.tercero.repository.ClienteRepository;
import com.hjoven.prueba.tercero.service.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Page<Cliente> getDatatable(Pageable pageable) {
        return null;
    }

    @Override
    public List<Cliente> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Cliente findById(Long id) throws Exception {
        Optional<Cliente> clienteOptional = this.repository.findById(id);
        if (clienteOptional.isEmpty()) throw new Exception("Registro no encontrado");
        return clienteOptional.get();
    }

    @Override
    public Cliente findByNumeroDocumento(String numeroDocumento) throws Exception {
        Optional<Cliente> clienteOptional = this.repository.findByIdentificacion(numeroDocumento);
        if (clienteOptional.isEmpty()) throw new Exception("Registro no encontrado");
        return clienteOptional.get();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return this.repository.save(cliente);
    }

    @Override
    public void update(Long id, Cliente cliente) throws Exception {
        Cliente clienteDatabase = this.findById(id);
        BeanUtils.copyProperties(cliente, clienteDatabase, GlobalConstants.EXCLUDED_FIELDS);
        this.repository.save(clienteDatabase);
    }

    @Override
    public void delete(Long id) throws Exception {
        this.repository.deleteById(id);
    }
}
