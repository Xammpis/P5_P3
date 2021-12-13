package com.P5.entities;

public class Voluntario_Internacional extends Voluntario {
    private String pais;

    public Voluntario_Internacional(int idPersona, String nombre, String nif, String direccion, Delegacion delegacion, String tareaDesepena, String tipoVoluntario, String pais) {
        super(idPersona, nombre, nif, direccion, delegacion, tareaDesepena, tipoVoluntario);
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return super.toString() + '\'' +
                ", Voluntario_Internacional{" +
                "pais='" + pais + '\'' +
                '}';
    }
}
