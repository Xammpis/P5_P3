package com.P5.DTO;

import com.P5.DAO.DAOFactory;
import com.P5.DAO.interfaces.IDelegacion;
import com.P5.entities.Delegacion;
import com.P5.entities.Personal;
import com.P5.entities.Proyecto;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

public class ProyectoDTO {
    public static Proyecto toEntity(Node proyectoNode) throws ParseException, ParserConfigurationException, SAXException, IOException, SQLException {
        Element proyectoXml = (Element) proyectoNode;
        Integer id = Integer.parseInt(proyectoXml.getAttribute("id"));
        String nombre = proyectoXml.getElementsByTagName("nombre").item(0).getTextContent();
        String pais = proyectoXml.getElementsByTagName("pais").item(0).getTextContent();
        String localizacion = proyectoXml.getElementsByTagName("localizacion").item(0).getTextContent();
        String lineaAccion = proyectoXml.getElementsByTagName("lineaAccion").item(0).getTextContent();
        String subLineaAccion = proyectoXml.getElementsByTagName("subLineaAccion").item(0).getTextContent();
        String fechaInicio = proyectoXml.getElementsByTagName("fechaInicio").item(0).getTextContent();
        String fechaFin = proyectoXml.getElementsByTagName("fechaFin").item(0).getTextContent();
        String socioLocal = proyectoXml.getElementsByTagName("socioLocal").item(0).getTextContent();
        String financiador = proyectoXml.getElementsByTagName("financiador").item(0).getTextContent();
        String financiacionAportada = proyectoXml.getElementsByTagName("financiacionAportada").item(0).getTextContent();

        ArrayList<Personal> personalAsociado = new ArrayList<>();
        NodeList personalAsociadoXml = proyectoXml.getElementsByTagName("personalAsociado").item(0).getChildNodes();
        for (int i = 0; i < personalAsociadoXml.getLength(); i++) {
            Element personalXml = (Element) proyectoNode;
            String personalId = personalXml.getElementsByTagName("personalId").item(0).getTextContent();
            personalAsociado.add(new Personal(Integer.parseInt(personalId), "", "", "", null));
        }

        String delegacionId = proyectoXml.getElementsByTagName("delegacionId").item(0).getTextContent();
        IDelegacion delegacionDao = DAOFactory.getDelegacionDAO(true);
        Delegacion delegacion = delegacionDao.readDelegacion(delegacionId);

        Date fechaInicioDate = (Date) new SimpleDateFormat("DD/MM/YYYY").parse(fechaInicio);
        Date fechaFinDate = (Date) new SimpleDateFormat("DD/MM/YYYY").parse(fechaFin);
        return new Proyecto(id, nombre, pais, localizacion, lineaAccion, subLineaAccion, fechaInicioDate, fechaFinDate, socioLocal, financiador, financiacionAportada, personalAsociado, delegacion);
    }
}
