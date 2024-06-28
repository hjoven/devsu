package com.hjoven.prueba.operaciones.controller;

import com.hjoven.prueba.operaciones.dto.EstadoCuentaDto;
import com.hjoven.prueba.operaciones.service.ReporteService;
import com.hjoven.prueba.shared.dtos.ApiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reportes")
public class ReporteController {
    @Autowired
    private ReporteService service;

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<EstadoCuentaDto>>> getEstadoCuentaReport(@RequestParam(name = "fecha") String fechas, @RequestParam(name = "identificacion") String identificacion) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.estadoCuentaReport(fechas, identificacion)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }
}
