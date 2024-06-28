package com.hjoven.prueba.tercero.repository;

import com.hjoven.prueba.shared.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByIdentificacion(String identificacion);
}
