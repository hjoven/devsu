package com.hjoven.prueba.operaciones.repository;

import com.hjoven.prueba.operaciones.dto.MovimientoEstadoCuentaDto;
import com.hjoven.prueba.shared.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    Optional<Movimiento> findTopByCuentaIdOrderByFechaDesc(Long cuentaId);

    @Query(value = "SELECT " +
            " new com.hjoven.prueba.operaciones.dto.MovimientoEstadoCuentaDto(mov.fecha, mov.tipoMovimiento, mov.valor, mov.saldo) " +
            " FROM Movimiento mov " +
            " WHERE mov.cuenta.id = :cuentaId AND" +
            " mov.fecha BETWEEN :fechaInicio AND :fechaFin " +
            " ORDER BY mov.id DESC ")
    List<MovimientoEstadoCuentaDto> findMovimientosByCuenta(Long cuentaId, LocalDate fechaInicio, LocalDate fechaFin);
}
