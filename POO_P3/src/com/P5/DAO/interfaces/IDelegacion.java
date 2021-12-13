package com.P5.DAO.interfaces;

import com.P5.entities.Delegacion;

import java.util.List;

public interface IDelegacion {
    public List<Delegacion> findAllDelegacion();

    public void createDelegacion(Delegacion delegacion);

    public Delegacion readDelegacion(String id);

    public Delegacion updateDelegacion(Delegacion delegacion);

    public void deleteDelegacion(String id);
}
