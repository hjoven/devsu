package com.hjoven.prueba.operaciones.controller;

import com.hjoven.prueba.operaciones.service.CuentaService;
import com.hjoven.prueba.shared.dtos.ApiResponseDto;
import com.hjoven.prueba.shared.entities.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cuentas")
public class CuentaController {

    @Autowired
    private CuentaService service;

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Cuenta>>> getAll() {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.getAll()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto<Cuenta>> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.findById(id)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @GetMapping("pageable")
    public ResponseEntity<ApiResponseDto<Page<Cuenta>>> getDatatable(@PageableDefault Pageable pageable) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.getDatatable(pageable)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<Cuenta>> save(@RequestBody Cuenta cuenta) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.save(cuenta)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<Cuenta>> update(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        try {
            this.service.update(id, cuenta);
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
