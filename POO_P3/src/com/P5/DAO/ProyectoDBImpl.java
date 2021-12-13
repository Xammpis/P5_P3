package com.P5.DAO;

import com.P5.DAO.interfaces.IProyecto;
import com.P5.entities.Personal;
import com.P5.entities.Proyecto;
import com.P5.utils.DbDonnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyectoDBImpl implements IProyecto {

    public ProyectoDBImpl() throws SQLException {
        String CREATE_TABLE_PROYECTO = "CREATE TABLE IF NOT EXISTS proyectos ("
                + "id int(11) AUTO_INCREMENT,"
                + "nombre VARCHAR(255) NOT NULL,"
                + "pais VARCHAR(255) NOT NULL,"
                + "localizacion VARCHAR(255) NOT NULL,"
                + "linea_accion VARCHAR(255) NOT NULL,"
                + "sublinea_accion VARCHAR(15) NOT NULL,"
                + "fecha_inicio DATE NOT NULL ,"
                + "fecha_fin DATE NOT NULL,"
                + "socio_local VARCHAR(255),"
                + "financiador VARCHAR(255),"
                + "financiacion_aportada VARCHAR(255),"
                + "delegacion_id int(11),"
                + "PRIMARY KEY (id),"
                + "FOREIGN KEY (delegacion_id) REFERENCES delegaciones(id))";

        String CREATE_TABLE_PROYECTO_PERSONAL = "CREATE TABLE IF NOT EXISTS proyecto_personal ("
                + "proyecto_id int(11),"
                + "personal_id int(11),"
                + "PRIMARY KEY (proyecto_id, personal_id),"
                + "FOREIGN KEY (proyecto_id) REFERENCES proyectos(id),"
                + "FOREIGN KEY (personal_id) REFERENCES personal(id))";

        Connection connection = DbDonnection.connectDatabase();

        PreparedStatement stmPorjectTable = connection.prepareStatement(CREATE_TABLE_PROYECTO);
        stmPorjectTable.executeUpdate();

        PreparedStatement stmProjectPersonalTable = connection.prepareStatement(CREATE_TABLE_PROYECTO_PERSONAL);
        stmProjectPersonalTable.executeUpdate();

        connection.close();
    }

    @Override
    public List<Proyecto> findProyectosDelegacion(String delegacionId) {
        List<Proyecto> projectsList = new ArrayList<>();

        try {
            DelegacionDBImpl delegacionDao = new DelegacionDBImpl();
            String FIND_PROYECTOS_DELEGACION = "SELECT * FROM proyectos WHERE delegacion_id = ?";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm = connection.prepareStatement(FIND_PROYECTOS_DELEGACION);
            stm.setInt(1, Integer.parseInt(delegacionId));
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                projectsList.add(new Proyecto(
                        res.getInt("id"),
                        res.getString("nombre"),
                        res.getString("pais"),
                        res.getString("localizacion"),
                        res.getString("linea_accion"),
                        res.getString("subLinea_accion"),
                        res.getDate("fecha_inicio"),
                        res.getDate("fecha_fin"),
                        res.getString("socio_local"),
                        res.getString("financiador"),
                        res.getString("financiacion_aportada"),
                        retrievePersonalAsociadoProyecto(res.getInt("id")),
                        delegacionDao.readDelegacion(delegacionId)
                ));
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return projectsList;
    }

    private ArrayList<Personal> retrievePersonalAsociadoProyecto(int proyectoId) {
        ArrayList<Personal> personalList = new ArrayList<>();

        try {
            PersonalDBImpl personalDao = new PersonalDBImpl();
            String FIND_PERSONAL_DELEGACION = "SELECT * FROM proyecto_personal WHERE proyecto_id = ?";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm = connection.prepareStatement(FIND_PERSONAL_DELEGACION);
            stm.setInt(1, proyectoId);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                personalList.add(personalDao.readPersonal(res.getInt("personal_id")));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return personalList;
    }

    @Override
    public Proyecto readProyecto(String nombre) {
        Proyecto p = null;

        try {
            DelegacionDBImpl delegacionDao = new DelegacionDBImpl();
            String FIND_PROYECTO = "SELECT * FROM proyectos WHERE nombre = ?";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm = connection.prepareStatement(FIND_PROYECTO);
            stm.setString(1, nombre);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                p = new Proyecto(
                        res.getInt("id"),
                        res.getString("nombre"),
                        res.getString("pais"),
                        res.getString("localizacion"),
                        res.getString("linea_accion"),
                        res.getString("subLinea_accion"),
                        res.getDate("fecha_inicio"),
                        res.getDate("fecha_fin"),
                        res.getString("socio_local"),
                        res.getString("financiador"),
                        res.getString("financiacion_aportada"),
                        retrievePersonalAsociadoProyecto(res.getInt("id")),
                        delegacionDao.readDelegacion(res.getString("delegacion_id"))
                );
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return p;
    }

    @Override
    public void createProyecto(Proyecto proyecto) {
        try {
            String CREATE_PROYECTO = "INSERT INTO proyectos (" +
                    "nombre, pais, localizacion, linea_accion, sublinea_accion, fecha_inicio, " +
                    "fecha_fin, socio_local, financiador, financiacion_aportada, delegacion_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stmProyecto = connection.prepareStatement(CREATE_PROYECTO, Statement.RETURN_GENERATED_KEYS);
            stmProyecto.setString(1, proyecto.getNombre());
            stmProyecto.setString(2, proyecto.getPais());
            stmProyecto.setString(3, proyecto.getLocalizacion());
            stmProyecto.setString(4, proyecto.getLineaAccion());
            stmProyecto.setString(5, proyecto.getSubLineaAccion());
            stmProyecto.setDate(6, proyecto.getFechaInicio());
            stmProyecto.setDate(7, proyecto.getFechaFin());
            stmProyecto.setString(8, proyecto.getSocioLocal());
            stmProyecto.setString(9, proyecto.getFinanciador());
            stmProyecto.setString(10, proyecto.getFinanciacionAportada());
            stmProyecto.setInt(11, proyecto.getDelegacion().getId());
            stmProyecto.executeUpdate();

            ResultSet rs = stmProyecto.getGeneratedKeys();
            int lastInsertedProyectId = 0;
            if (rs.next()) {
                lastInsertedProyectId = rs.getInt(1);
            }

            if (lastInsertedProyectId > 0) {
                for (int i = 0; i < proyecto.getPersonalAsociado().size(); i++) {
                    String CREATE_PROYECTO_PERSONAL = "INSERT INTO proyecto_personal (proyecto_id, personal_id)" +
                            "VALUES (?, ?)";
                    PreparedStatement stmProyectoPersonal = connection.prepareStatement(CREATE_PROYECTO_PERSONAL);
                    stmProyectoPersonal.setInt(1, lastInsertedProyectId);
                    stmProyectoPersonal.setInt(2, proyecto.getPersonalAsociado().get(i).getIdPersona());
                    stmProyectoPersonal.executeUpdate();
                }
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Proyecto updateProyecto(Proyecto proyecto) {
        try {
            String UPDATE_PROYECTO = "UPDATE proyectos SET nombre = ?, pais = ?, localizacion = ?, linea_accion = ?, " +
                    "subLinea_accion = ?, fecha_inicio = ?, fecha_fin = ?, socio_local = ?, financiador = ?, " +
                    "financiacion_aportada = ? " +
                    "WHERE id = ?";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm = connection.prepareStatement(UPDATE_PROYECTO);
            stm.setString(1, proyecto.getNombre());
            stm.setString(2, proyecto.getPais());
            stm.setString(3, proyecto.getLocalizacion());
            stm.setString(4, proyecto.getLineaAccion());
            stm.setString(5, proyecto.getSubLineaAccion());
            stm.setDate(6, proyecto.getFechaInicio());
            stm.setDate(7, proyecto.getFechaFin());
            stm.setString(8, proyecto.getSocioLocal());
            stm.setString(9, proyecto.getFinanciador());
            stm.setString(10, proyecto.getFinanciacionAportada());
            stm.setInt(11, proyecto.getId());
            stm.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return proyecto;
    }

    @Override
    public void deleteProyecto(String nombre) {
        try {
            String SELETECT_PROYECTO = "SELECT * FROM proyectos WHERE nombre = ?";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stmSelectProyect = connection.prepareStatement(SELETECT_PROYECTO);
            stmSelectProyect.setString(1, nombre);
            ResultSet res = stmSelectProyect.executeQuery();

            while (res.next()) {
                int proyectId = res.getInt("id");

                String DELETE_PROYECTO_PERSONAL = "DELETE FROM proyecto_personal WHERE proyecto_id = ?";
                PreparedStatement stmProyectoPersonal = connection.prepareStatement(DELETE_PROYECTO_PERSONAL);
                stmProyectoPersonal.setInt(1, proyectId);
                stmProyectoPersonal.executeUpdate();

                String DELETE_PROYECTO = "DELETE FROM proyectos WHERE nombre = ?";
                PreparedStatement stm = connection.prepareStatement(DELETE_PROYECTO);
                stm.setString(1, nombre);
                stm.executeUpdate();
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
