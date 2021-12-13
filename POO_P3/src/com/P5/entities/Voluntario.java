package com.P5.entities;

public class Voluntario extends Personal {
    private String tareaDesepena;
    private String tipoVoluntario;

    public Voluntario(int idPersona, String nombre, String nif, String direccion, Delegacion delegacion, String tareaDesepena, String tipoVoluntario) {
        super(idPersona, nombre, nif, direccion, delegacion);
        this.tareaDesepena = tareaDesepena;
        this.tipoVoluntario = tipoVoluntario;
    }

    public String getTareaDesepena() {
        return tareaDesepena;
    }

    public void setTareaDesepena(String tareaDesepena) {
        this.tareaDesepena = tareaDesepena;
    }

    public String getTipoVoluntario() {
        return tipoVoluntario;
    }

    public void setTipoVoluntario(String tipoVoluntario) {
        this.tipoVoluntario = tipoVoluntario;
    }

    @Override
    public String toString() {
        return super.toString() + '\'' +
                ", Voluntario{" +
                "tareaDesepena='" + tareaDesepena + '\'' +
                ", tipoVoluntario='" + tipoVoluntario + '\'' +
                '}';
    }
}
