package com.P5.entities;

public class Socios extends Donante {

    private float cuota;
    private String tipoCuota;

    public Socios(String nombre, String apellido, String dni, String direccion, String cuentaBancacia, Delegacion delegacion, float cuota, String tipoCuota) {
        super(nombre, apellido, dni, direccion, cuentaBancacia, delegacion);
        this.cuota = cuota;
        this.tipoCuota = tipoCuota;
    }

    public float getCuota() {
        return cuota;
    }

    public void setCuota(float cuota) {
        this.cuota = cuota;
    }

    public String getTipoCuota() {
        return tipoCuota;
    }

    public void setTipoCuota(String tipoCuota) {
        this.tipoCuota = tipoCuota;
    }
}
