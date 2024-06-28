package com.hjoven.prueba.operaciones.dto;

import java.util.List;

public class EstadoCuentaDto {
    private String cliente;
    private String numeroCuenta;
    private Float saldo;
    private List<MovimientoEstadoCuentaDto> movimientoEstadoCuentaDtos;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public List<MovimientoEstadoCuentaDto> getMovimientoEstadoCuentaDtos() {
        return movimientoEstadoCuentaDtos;
    }

    public void setMovimientoEstadoCuentaDtos(List<MovimientoEstadoCuentaDto> movimientoEstadoCuentaDtos) {
        this.movimientoEstadoCuentaDtos = movimientoEstadoCuentaDtos;
    }
}
