package com.hjoven.prueba.operaciones.repository;

import com.hjoven.prueba.shared.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    @Query(value = "SELECT cu FROM Cuenta cu WHERE cu.cliente.identificacion = :identificacion")
    List<Cuenta> findByClienteIdentificacion(String identificacion);
}
