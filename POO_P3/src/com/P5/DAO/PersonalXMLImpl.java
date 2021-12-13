package com.P5.DAO;

import com.P5.DAO.interfaces.IPersonal;
import com.P5.DTO.PersonalDTO;
import com.P5.entities.*;
import com.P5.xml.XMLManager;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PersonalXMLImpl implements IPersonal {

    XMLManager xmlFactory = new XMLManager();

    public PersonalXMLImpl() throws IOException, SAXException, ParserConfigurationException {
        super();
    }

    @Override
    public List<Personal> findPersonalDelegacion(String delegacionId) {
        List<Personal> personalList = new ArrayList<>();

        try {
            NodeList personal = this.xmlFactory.recuperarElemento("personal");
            for (int i = 0; i < personal.getLength(); i++) {
                Node nNode = personal.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Node personalProyecto = this.xmlFactory.recuperarElementoEnElemento("delegacionId", nNode);
                    if (personalProyecto != null && personalProyecto.getTextContent().equals(delegacionId)) {
                        personalList.add(PersonalDTO.toEntity(nNode));
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | ParseException | IOException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return personalList;
    }

    @Override
    public void createPersonal(Personal personal) {
        try {
            Element personalXml = this.xmlFactory.crearElementoPersonal(personal);
            this.xmlFactory.anhadirElemento("listaPersonal", personalXml);
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Personal readPersonal(int idPersonal) {
        try {
            Node listaPersonal = this.xmlFactory.recuperarElemento("listaPersonal").item(0);
            NodeList personal = listaPersonal.getChildNodes();
            for (int i = 0; i < personal.getLength(); i++) {
                Node nNode = personal.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element personalXml = (Element) nNode;
                    int idPersonalXml = Integer.parseInt(personalXml.getAttribute("id"));
                    if (idPersonalXml == idPersonal) {
                        return PersonalDTO.toEntity(personalXml);
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | ParseException | IOException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Personal updatePersonal(Personal persona) {
        Node listaPersonal = this.xmlFactory.recuperarElemento("listaPersonal").item(0);
        NodeList personal = listaPersonal.getChildNodes();
        for (int i = 0; i < personal.getLength(); i++) {
            Node nNode = personal.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element personalXml = (Element) nNode;
                int idPersonalXml = Integer.parseInt(personalXml.getAttribute("id"));
                if (idPersonalXml == persona.getIdPersona()) {
                    personalXml.getElementsByTagName("nombre").item(0).setTextContent(persona.getNombre());
                    personalXml.getElementsByTagName("nif").item(0).setTextContent(persona.getNif());
                    personalXml.getElementsByTagName("direccion").item(0).setTextContent(persona.getDireccion());
                    personalXml.getElementsByTagName("delegacionId").item(0).setTextContent(Integer.toString(persona.getDelegacion().getId()));

                    String tipoPersonalClass = this.xmlFactory.recuperarElementoEnElemento("tipoPersonal", personalXml).getTextContent();
                    switch (tipoPersonalClass) {
                        case "Empleado":
                            personalXml.getElementsByTagName("salario").item(0).setTextContent(Float.toString(((Empleado) persona).getSalario()));
                            break;
                        case "Colaborador":
                            personalXml.getElementsByTagName("areaColaboracion").item(0).setTextContent(((Colaborador) persona).getAreaColaboracion());
                            break;
                        case "Voluntario_Nacional":
                            personalXml.getElementsByTagName("tareaDesempena").item(0).setTextContent(((Voluntario_Nacional) persona).getTareaDesepena());
                            personalXml.getElementsByTagName("ciudad").item(0).setTextContent(((Voluntario_Nacional) persona).getCiudad());
                            break;
                        case "Voluntario_Internacional":
                            personalXml.getElementsByTagName("tareaDesempena").item(0).setTextContent(((Voluntario_Internacional) persona).getTareaDesepena());
                            personalXml.getElementsByTagName("pais").item(0).setTextContent(((Voluntario_Internacional) persona).getPais());
                            break;
                    }

                    try {
                        this.xmlFactory.eliminarElemento("listaPersonal", "personal", persona.getNombre());
                        this.xmlFactory.anhadirElemento("listaPersonal", personalXml);
                    } catch (TransformerException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        return persona;
    }

    @Override
    public void deletePersonal(int idPersonal) {
        NodeList personal = this.xmlFactory.recuperarElemento("personal");
        for (int i = 0; i < personal.getLength(); i++) {
            Element personalXml = (Element) personal.item(i);
            String id = Integer.toString(idPersonal);
            if (personalXml.getAttribute("id").equals(id)) {
                try {
                    this.xmlFactory.eliminarElemento("listaPersonal", "personal", id);
                } catch (TransformerException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
