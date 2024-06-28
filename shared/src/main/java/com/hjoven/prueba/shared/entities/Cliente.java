package com.hjoven.prueba.shared.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Cliente extends Persona {

    @Column(name = "contrasenia", length = 200, nullable = false)
    private String contrasenia;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
