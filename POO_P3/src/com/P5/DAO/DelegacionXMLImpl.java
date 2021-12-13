package com.P5.DAO;

import com.P5.DAO.interfaces.IDelegacion;
import com.P5.DTO.DelegacionDTO;
import com.P5.entities.Delegacion;
import com.P5.xml.XMLManager;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DelegacionXMLImpl implements IDelegacion {

    XMLManager xmlFactory = new XMLManager();

    public DelegacionXMLImpl() throws IOException, SAXException, ParserConfigurationException {
    }

    @Override
    public List<Delegacion> findAllDelegacion() {
        List<Delegacion> delegacionesList = new ArrayList<>();
        NodeList delegaciones = this.xmlFactory.recuperarElemento("delegacion");
        for (int i = 0; i < delegaciones.getLength(); i++) {
            Node nNode = delegaciones.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                delegacionesList.add(DelegacionDTO.toEntity(nNode));
            }
        }

        return delegacionesList;
    }

    @Override
    public void createDelegacion(Delegacion delegacion) {
        try {
            Element delegacionXml = this.xmlFactory.crearElementoDelegacion(delegacion);
            this.xmlFactory.anhadirElemento("delegaciones", delegacionXml);
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Delegacion readDelegacion(String id) {
        NodeList delegaciones = this.xmlFactory.recuperarElemento("delegacion");
        for (int i = 0; i < delegaciones.getLength(); i++) {
            Element delegacionXML = (Element) delegaciones.item(i);
            if (delegacionXML.getAttribute("id").equals(id)) {
                return DelegacionDTO.toEntity(delegacionXML);
            }
        }

        return null;
    }

    @Override
    public Delegacion updateDelegacion(Delegacion delegacion) {
        NodeList delegaciones = this.xmlFactory.recuperarElemento("delegacion");
        for (int i = 0; i < delegaciones.getLength(); i++) {
            Element delegacionXML = (Element) delegaciones.item(i);
            if (delegacionXML.getAttribute("id").equals(delegacion.getId().toString())) {
                delegacionXML.getElementsByTagName("ciudad").item(0).setTextContent(delegacion.getCiudad());
                delegacionXML.getElementsByTagName("direccion").item(0).setTextContent(delegacion.getDireccion());
                delegacionXML.getElementsByTagName("telefono").item(0).setTextContent(delegacion.getTelefono());
                delegacionXML.getElementsByTagName("email").item(0).setTextContent(delegacion.getEmail());
                delegacionXML.getElementsByTagName("central").item(0).setTextContent(Boolean.toString(delegacion.getCentral()));

                try {
                    this.xmlFactory.eliminarElemento("delegaciones", "delegacion", delegacion.getId().toString());
                    this.xmlFactory.anhadirElemento("delegaciones", delegacionXML);
                } catch (TransformerException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return delegacion;
    }

    @Override
    public void deleteDelegacion(String id) {
        NodeList delegaciones = this.xmlFactory.recuperarElemento("delegacion");
        for (int i = 0; i < delegaciones.getLength(); i++) {
            Element delegacionXML = (Element) delegaciones.item(i);
            if (delegacionXML.getAttribute("id").equals(id)) {
                try {
                    this.xmlFactory.eliminarElemento("delegaciones", "delegacion", id);
                } catch (TransformerException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
