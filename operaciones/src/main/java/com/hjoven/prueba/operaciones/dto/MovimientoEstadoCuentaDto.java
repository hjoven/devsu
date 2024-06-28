package com.hjoven.prueba.operaciones.dto;

import java.time.LocalDate;

public class MovimientoEstadoCuentaDto {
    private LocalDate fecha;
    private Integer tipoMovimiento;
    private Float valor;
    private Float saldo;

    public MovimientoEstadoCuentaDto(LocalDate fecha, Integer tipoMovimiento, Float valor, Float saldo) {
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Integer tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
}
