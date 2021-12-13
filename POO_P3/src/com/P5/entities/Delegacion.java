package com.P5.entities;


public class Delegacion {
    private final Integer id;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String email;
    private Boolean central;


    public Delegacion(Integer id, String ciudad, String direccion, String telefono, String email, Boolean central) {
        this.id = id;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.central = central;

    }

    public Integer getId() {
        return id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getCentral() { return central; }

    public void setCentral(Boolean central) { this.central = central; }

    @Override
    public String toString() {
        return "Delegacion{" +
                "id=" + id +
                ", ciudad='" + ciudad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", delegacion=" + central +
                '}';
    }
}
