package com.P5.DAO.interfaces;

import com.P5.entities.Proyecto;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface IProyecto {
    public List<Proyecto> findProyectosDelegacion(String delgacionId) throws ParserConfigurationException, SAXException, ParseException, IOException;

    public Proyecto readProyecto(String id) throws ParserConfigurationException, SAXException, ParseException, IOException;

    public void createProyecto(Proyecto proyecto);

    public Proyecto updateProyecto(Proyecto proyecto);

    public void deleteProyecto(String id);
}
