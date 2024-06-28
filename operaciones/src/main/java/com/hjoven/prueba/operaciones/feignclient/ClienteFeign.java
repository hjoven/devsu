package com.hjoven.prueba.operaciones.feignclient;

import com.hjoven.prueba.shared.dtos.ApiResponseDto;
import com.hjoven.prueba.shared.entities.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tercero")
public interface ClienteFeign {

    @GetMapping("cuentas/{id}")
    ApiResponseDto<Cliente> getById(@PathVariable Long id);

}
