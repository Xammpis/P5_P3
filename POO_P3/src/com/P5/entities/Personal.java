package com.P5.entities;

public class Personal {
    private int idPersona;
    private String nombre;
    private String nif;
    private String direccion;
    private Delegacion delegacion;

    public Personal(int idPersona, String nombre, String nif, String direccion, Delegacion delegacion) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.delegacion = delegacion;
    }

    public Personal(int idPersona, String pau, String nif, String calle_falsa) {
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Delegacion getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(Delegacion delegacion) {
        this.delegacion = delegacion;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", nif='" + nif + '\'' +
                ", direccion='" + direccion + '\'' +
                ", delegacion=" + delegacion +
                '}';
    }
}
