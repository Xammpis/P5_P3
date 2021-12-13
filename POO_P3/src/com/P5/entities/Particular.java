package com.P5.entities;

import com.P5.enums.TipoAportacion;

public class Particular extends Donante {

    private TipoAportacion tipoAportacion;

    public Particular(String nombre, String apellido, String dni, String cuentaBancacia, String direccion, Delegacion delegacion, TipoAportacion tipoAportacion) {
        super(nombre, apellido, dni, cuentaBancacia, direccion, delegacion);
        this.tipoAportacion = tipoAportacion;
    }

    public TipoAportacion getTipoAportacion() {
        return tipoAportacion;
    }

    public void setTipoAportacion(TipoAportacion tipoAportacion) {
        this.tipoAportacion = tipoAportacion;
    }
}
