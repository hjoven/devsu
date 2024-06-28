package com.hjoven.prueba.operaciones.controller;

import com.hjoven.prueba.operaciones.service.MovimientoService;
import com.hjoven.prueba.shared.dtos.ApiResponseDto;
import com.hjoven.prueba.shared.entities.Cuenta;
import com.hjoven.prueba.shared.entities.Movimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService service;

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Movimiento>>> getAll() {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.getAll()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto<Movimiento>> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.findById(id)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @GetMapping("pageable")
    public ResponseEntity<ApiResponseDto<Page<Movimiento>>> getDatatable(@PageableDefault Pageable pageable) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.getDatatable(pageable)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<Movimiento>> save(@RequestBody Movimiento movimiento) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.save(movimiento)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<Movimiento>> update(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        try {
            this.service.update(id, movimiento);
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<Cuenta>> delete(@PathVariable Long id) {
        try {
            this.service.delete(id);
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }
}
