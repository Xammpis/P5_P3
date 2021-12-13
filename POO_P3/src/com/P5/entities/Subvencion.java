package com.P5.entities;

public class Subvencion {
    private String entidad;
    private String tipoEntidad;

    public Subvencion(String entidad, String tipoEntidad) {
        this.entidad = entidad;
        this.tipoEntidad = tipoEntidad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }
}
