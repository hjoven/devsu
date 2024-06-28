package com.hjoven.prueba.tercero.service;

import com.hjoven.prueba.shared.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {
    Page<Cliente> getDatatable(Pageable pageable);
    List<Cliente> getAll();
    Cliente findById(Long id) throws Exception;
    Cliente findByNumeroDocumento(String numeroDocumento) throws Exception;
    Cliente save(Cliente cliente);
    void update(Long id, Cliente cliente) throws Exception;
    void delete(Long id) throws Exception;
}
