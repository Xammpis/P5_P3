package com.P5.DTO;

import com.P5.entities.Delegacion;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DelegacionDTO {
    public static Delegacion toEntity(Node delegacionNode) {
        Element delegacionXML = (Element) delegacionNode;
        Integer id = Integer.parseInt(delegacionXML.getAttribute("id"));
        String ciudad = delegacionXML.getElementsByTagName("ciudad").item(0).getTextContent();
        String direccion = delegacionXML.getElementsByTagName("direccion").item(0).getTextContent();
        String telefono = delegacionXML.getElementsByTagName("telefono").item(0).getTextContent();
        String email = delegacionXML.getElementsByTagName("email").item(0).getTextContent();
        Boolean central = Boolean.parseBoolean(delegacionXML.getElementsByTagName("central").item(0).getTextContent());

        return new Delegacion(id, ciudad, direccion, telefono, email, central);
    }
}
