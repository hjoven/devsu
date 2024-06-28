package com.hjoven.prueba.operaciones.service;

import com.hjoven.prueba.operaciones.dto.EstadoCuentaDto;

import java.time.LocalDate;
import java.util.List;

public interface ReporteService {
    List<EstadoCuentaDto> estadoCuentaReport(String fechas, String identificacion);
}
