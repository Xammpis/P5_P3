package com.P5.DAO.interfaces;


import com.P5.entities.Personal;

import java.util.List;

public interface IPersonal {

    public List<Personal> findPersonalDelegacion(String delegacionId);

    public void createPersonal(Personal personal);

    public Personal readPersonal(int idPersonal);

    public Personal updatePersonal(Personal persona);

    public void deletePersonal(int idPersonal);
}
