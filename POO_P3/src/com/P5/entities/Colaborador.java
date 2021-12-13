package com.P5.entities;

public class Colaborador extends Personal {

    private String areaColaboracion;

    public Colaborador(int idPersona, String nombre, String nif, String direccion, Delegacion delegacion, String areaColaboracion) {
        super(idPersona, nombre, nif, direccion, delegacion);
        this.areaColaboracion = areaColaboracion;
    }

    public String getAreaColaboracion() {
        return areaColaboracion;
    }

    public void setAreaColaboracion(String areaColaboracion) {
        this.areaColaboracion = areaColaboracion;
    }

    @Override
    public String toString() {
        return super.toString() + '\'' +
                ", Colaborador{" +
                "areaColaboracion='" + areaColaboracion + '\'' +
                '}';
    }
}
