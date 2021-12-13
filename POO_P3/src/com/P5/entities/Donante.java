package com.P5.entities;

public class Donante {

    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private String cuentaBancacia;
    private Delegacion delegacion;

    public Donante(String nombre, String apellido, String dni, String direccion, String cuentaBancacia, Delegacion delegacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.cuentaBancacia = cuentaBancacia;
        this.delegacion = delegacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCuentaBancacia() {
        return cuentaBancacia;
    }

    public void setCuentaBancacia(String cuentaBancacia) {this.cuentaBancacia = cuentaBancacia;}

    public Delegacion getDelegacion() { return delegacion; }

    public void setDelegacion(Delegacion delegacion) { this.delegacion = delegacion; }
}
