package com.P5.entities;

public class Voluntario_Nacional extends Voluntario {
    private String ciudad;

    public Voluntario_Nacional(int idPersona, String nombre, String nif, String direccion, Delegacion delegacion, String tareaDesepena, String tipoVoluntario, String ciudad) {
        super(idPersona, nombre, nif, direccion, delegacion, tareaDesepena, tipoVoluntario);
        this.ciudad = ciudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return super.toString() + '\'' +
                ", Voluntario_Nacional{" +
                "ciudad='" + ciudad + '\'' +
                '}';
    }
}
