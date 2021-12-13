package com.P5.entities;

import com.P5.enums.TipoPago;

import java.sql.Date;

public class Aportacion {
    private float importe;
    private String moneda;
    private TipoPago tipoPago;
    private Date fecha;
    private Donante donante;
    private Subvencion subvencion;
    private Empresa empresa;

    public Aportacion(float importe, String moneda, TipoPago tipoPago, Date fecha, Donante donante, Subvencion subvencion, Empresa empresa) {
        this.importe = importe;
        this.moneda = moneda;
        this.tipoPago = tipoPago;
        this.fecha = fecha;
        this.donante = donante;
        this.subvencion = subvencion;
        this.empresa = empresa;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Donante getDonante() {
        return donante;
    }

    public void setDonante(Donante donante) {
        this.donante = donante;
    }

    public Subvencion getSubvencion() {
        return subvencion;
    }

    public void setSubvencion(Subvencion subvencion) {
        this.subvencion = subvencion;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
