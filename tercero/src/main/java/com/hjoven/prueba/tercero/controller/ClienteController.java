package com.hjoven.prueba.tercero.controller;

import com.hjoven.prueba.shared.dtos.ApiResponseDto;
import com.hjoven.prueba.shared.entities.Cliente;
import com.hjoven.prueba.tercero.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Cliente>>> getAll() {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.getAll()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto<Cliente>> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.findById(id)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @GetMapping("findByIdentificacion/{identificacion}")
    public ResponseEntity<ApiResponseDto<Cliente>> getByIdentificacion(@PathVariable String identificacion) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.findByNumeroDocumento(identificacion)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @GetMapping("pageable")
    public ResponseEntity<ApiResponseDto<Page<Cliente>>> getDatatable(@PageableDefault Pageable pageable) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.getDatatable(pageable)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<Cliente>> save(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true, this.service.save(cliente)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<Cliente>> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            this.service.update(id, cliente);
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<Cliente>> delete(@PathVariable Long id) {
        try {
            this.service.delete(id);
            return ResponseEntity.ok(new ApiResponseDto<>("OK", true));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(ex.getMessage(), false));
        }
    }
}
