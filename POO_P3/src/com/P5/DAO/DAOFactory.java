package com.P5.DAO;

import com.P5.DAO.interfaces.IDelegacion;
import com.P5.DAO.interfaces.IPersonal;
import com.P5.DAO.interfaces.IProyecto;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class DAOFactory {

    public static IDelegacion getDelegacionDAO(boolean useDb) throws ParserConfigurationException, SAXException, IOException, SQLException {
        if (useDb) {
            return new DelegacionDBImpl();
        } else {
            return new DelegacionXMLImpl();
        }
    }

    public static IPersonal getPersonalDAO(boolean useDb) throws ParserConfigurationException, SAXException, IOException, SQLException {
        if (useDb) {
            return new PersonalDBImpl();
        } else {
            return new PersonalXMLImpl();
        }
    }

    public static IProyecto getProyectoDAO(boolean useDb) throws ParserConfigurationException, SAXException, IOException, SQLException {
        if (useDb) {
            return new ProyectoDBImpl();
        } else {
            return new ProyectoXMLImpl();
        }
    }

}
