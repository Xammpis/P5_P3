package com.P5.Test;


import com.P5.entities.Delegacion;
import com.P5.entities.Personal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

class PersonalTest {

    @Test
    void setIdPersona() {

        Delegacion delegacion = new Delegacion(1,"Barcelona","609875485","entreculturas@gmail.com", "akksjadkjsd",true);
        Personal personal = new Personal(1,"Pau","4254654654654", "calle falsa",delegacion);


        int esperado = 1;
        int real = personal.getIdPersona();
        Assertions.assertEquals(esperado, real);
    }


}