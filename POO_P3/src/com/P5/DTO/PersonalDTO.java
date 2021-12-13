package com.P5.DTO;

import com.P5.DAO.DAOFactory;
import com.P5.DAO.interfaces.IDelegacion;
import com.P5.entities.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class PersonalDTO {
    public static Personal toEntity(Node proyectoNode) throws ParseException, ParserConfigurationException, SAXException, IOException, SQLException {
        Element personalXml = (Element) proyectoNode;
        Integer id = Integer.parseInt(personalXml.getAttribute("id"));
        String nombre = personalXml.getElementsByTagName("nombre").item(0).getTextContent();
        String nif = personalXml.getElementsByTagName("nif").item(0).getTextContent();
        String direccion = personalXml.getElementsByTagName("direccion").item(0).getTextContent();

        String delegacionId = personalXml.getElementsByTagName("delegacionId").item(0).getTextContent();
        IDelegacion delegacionDao = DAOFactory.getDelegacionDAO(true);
        Delegacion delegacion = delegacionDao.readDelegacion(delegacionId);

        String tipoPersonal = personalXml.getElementsByTagName("tipoPersonal").item(0).getTextContent();
        String tareaDesempena;
        Personal personal = new Personal(id, nombre, nif, direccion, delegacion);
        switch (tipoPersonal) {
            case "Empleado":
                String salarioStr = personalXml.getElementsByTagName("salario").item(0).getTextContent();
                float salario = Float.parseFloat(salarioStr);
                personal = new Empleado(id, nombre, nif, direccion, delegacion, salario);
                break;
            case "Colaborador":
                String areaColaboracion = personalXml.getElementsByTagName("areaColaboracion").item(0).getTextContent();
                personal = new Colaborador(id, nombre, nif, direccion, delegacion, areaColaboracion);
                break;
            case "Voluntario_Nacional":
                tareaDesempena = personalXml.getElementsByTagName("tareaDesempena").item(0).getTextContent();
                String ciudad = personalXml.getElementsByTagName("ciudad").item(0).getTextContent();
                personal = new Voluntario_Nacional(id, nombre, nif, direccion, delegacion, tareaDesempena, "Nacional", ciudad);
                break;
            case "Voluntario_Internacional":
                tareaDesempena = personalXml.getElementsByTagName("tareaDesempena").item(0).getTextContent();
                String pais = personalXml.getElementsByTagName("pais").item(0).getTextContent();
                personal = new Voluntario_Internacional(id, nombre, nif, direccion, delegacion, tareaDesempena, "Internacional", pais);
                break;
        }

        return personal;
    }
}
