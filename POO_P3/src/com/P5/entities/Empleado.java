package com.P5.entities;

public class Empleado extends Personal {

    private float salario;

    public Empleado(int idPersona, String nombre, String dni, String direccion, Delegacion delegacion, float salario) {
        super(idPersona, nombre, dni, direccion, delegacion);
        this.salario = salario;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return super.toString() + '\'' +
                ", Empleado{" +
                "salario=" + salario +
                '}';
    }
}
