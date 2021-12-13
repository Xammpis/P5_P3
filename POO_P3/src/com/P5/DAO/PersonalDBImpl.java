package com.P5.DAO;

import com.P5.DAO.interfaces.IPersonal;
import com.P5.entities.*;
import com.P5.utils.DbDonnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalDBImpl implements IPersonal {

    public PersonalDBImpl() throws SQLException {
        String CREATE_TABLE_PERSONAL = "CREATE TABLE IF NOT EXISTS personal ("
                + "id int(11) AUTO_INCREMENT,"
                + "nombre VARCHAR(255) NOT NULL,"
                + "nif VARCHAR(255) NOT NULL,"
                + "direccion VARCHAR(255) NOT NULL,"
                + "tipo_personal VARCHAR(15) NOT NULL,"
                + "salario FLOAT,"
                + "area_colaboracion VARCHAR(255),"
                + "tarea_desempena VARCHAR(255),"
                + "tipo_voluntario VARCHAR(15),"
                + "ciudad VARCHAR(255),"
                + "pais VARCHAR(255),"
                + "delegacion_id int(11),"
                + "PRIMARY KEY (id),"
                + "FOREIGN KEY (delegacion_id) REFERENCES delegaciones(id))";
        Connection connection = DbDonnection.connectDatabase();
        PreparedStatement stm = connection.prepareStatement(CREATE_TABLE_PERSONAL);
        stm.executeUpdate();
        connection.close();
    }

    @Override
    public List<Personal> findPersonalDelegacion(String delegacionId) {
        List<Personal> personalList = new ArrayList<>();

        try {
            DelegacionDBImpl delegacionDao = new DelegacionDBImpl();
            String FIND_PERSONAL_DELEGACION = "SELECT * FROM personal WHERE delegacion_id = ?";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm = connection.prepareStatement(FIND_PERSONAL_DELEGACION);
            stm.setInt(1, Integer.parseInt(delegacionId));
            ResultSet res = stm.executeQuery();
            Personal p = null;
            while (res.next()) {
                String tipoPersonal = res.getString("tipo_personal");
                switch (tipoPersonal) {
                    case "empleado":
                        p = new Empleado(
                                res.getInt("id"),
                                res.getString("nombre"),
                                res.getString("nif"),
                                res.getString("direccion"),
                                delegacionDao.readDelegacion(delegacionId),
                                res.getFloat("salario")
                        );
                        break;
                    case "colaborador":
                        p = new Colaborador(
                                res.getInt("id"),
                                res.getString("nombre"),
                                res.getString("nif"),
                                res.getString("direccion"),
                                delegacionDao.readDelegacion(delegacionId),
                                res.getString("area_colaboracion")
                        );
                        break;
                    case "nacional":
                        p = new Voluntario_Nacional(
                                res.getInt("id"),
                                res.getString("nombre"),
                                res.getString("nif"),
                                res.getString("direccion"),
                                delegacionDao.readDelegacion(delegacionId),
                                res.getString("tarea_desempena"),
                                res.getString("tipo_voluntario"),
                                res.getString("ciudad")
                        );
                        break;
                    case "internacional":
                        p = new Voluntario_Internacional(
                                res.getInt("id"),
                                res.getString("nombre"),
                                res.getString("nif"),
                                res.getString("direccion"),
                                delegacionDao.readDelegacion(delegacionId),
                                res.getString("tarea_desempena"),
                                res.getString("tipo_voluntario"),
                                res.getString("pais")
                        );
                        break;
                }

                personalList.add(p);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return personalList;
    }

    @Override
    public void createPersonal(Personal personal) {
        try {
            String[] tipoPersonalClassSplit = personal.getClass().getName().split("\\.");
            String tipoPersonalClass = tipoPersonalClassSplit[tipoPersonalClassSplit.length - 1];

            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm;
            switch (tipoPersonalClass) {
                case "Empleado":
                    String CREATE_EMPLEADO = "INSERT INTO personal (nombre, nif, direccion, tipo_personal, salario, delegacion_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";
                    stm = connection.prepareStatement(CREATE_EMPLEADO);
                    stm.setString(1, personal.getNombre());
                    stm.setString(2, personal.getNif());
                    stm.setString(3, personal.getDireccion());
                    stm.setString(4, "empleado");
                    stm.setFloat(5, ((Empleado) personal).getSalario());
                    stm.setInt(6, personal.getDelegacion().getId());
                    stm.executeUpdate();
                    break;
                case "Colaborador":
                    String CREATE_COLABORADOR = "INSERT INTO personal (nombre, nif, direccion, tipo_personal, area_colaboracion, delegacion_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";
                    stm = connection.prepareStatement(CREATE_COLABORADOR);
                    stm.setString(1, personal.getNombre());
                    stm.setString(2, personal.getNif());
                    stm.setString(3, personal.getDireccion());
                    stm.setString(4, "colaborador");
                    stm.setString(5, ((Colaborador) personal).getAreaColaboracion());
                    stm.setInt(6, personal.getDelegacion().getId());
                    stm.executeUpdate();
                    break;
                case "Voluntario_Nacional":
                    String CREATE_VOLUNTARIO_NACIONAL = "INSERT INTO personal (nombre, nif, direccion, tipo_personal, tarea_desempena, tipo_voluntario, ciudad, delegacion_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    stm = connection.prepareStatement(CREATE_VOLUNTARIO_NACIONAL);
                    stm.setString(1, personal.getNombre());
                    stm.setString(2, personal.getNif());
                    stm.setString(3, personal.getDireccion());
                    stm.setString(4, "nacional");
                    stm.setString(5, ((Voluntario) personal).getTareaDesepena());
                    stm.setString(6, "nacional");
                    stm.setString(7, ((Voluntario_Nacional) personal).getCiudad());
                    stm.setInt(8, personal.getDelegacion().getId());
                    stm.executeUpdate();
                    break;
                case "Voluntario_Internacional":
                    String CREATE_VOLUNTARIO_INTERNACIONAL = "INSERT INTO personal (nombre, nif, direccion, tipo_personal, tarea_desempena, tipo_voluntario, pais, delegacion_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    stm = connection.prepareStatement(CREATE_VOLUNTARIO_INTERNACIONAL);
                    stm.setString(1, personal.getNombre());
                    stm.setString(2, personal.getNif());
                    stm.setString(3, personal.getDireccion());
                    stm.setString(4, "internacional");
                    stm.setString(5, ((Voluntario) personal).getTareaDesepena());
                    stm.setString(6, "internacional");
                    stm.setString(7, ((Voluntario_Internacional) personal).getPais());
                    stm.setInt(8, personal.getDelegacion().getId());
                    stm.executeUpdate();
                    break;
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Personal readPersonal(int idPersonal) {
        Personal p = null;

        try {
            DelegacionDBImpl delegacionDao = new DelegacionDBImpl();
            String FIND_PERSONAL = "SELECT * FROM personal WHERE id = ?";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm = connection.prepareStatement(FIND_PERSONAL);
            stm.setInt(1, idPersonal);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                String tipoPersonal = res.getString("tipo_personal");
                switch (tipoPersonal) {
                    case "empleado":
                        p = new Empleado(
                                res.getInt("id"),
                                res.getString("nombre"),
                                res.getString("nif"),
                                res.getString("direccion"),
                                delegacionDao.readDelegacion(Integer.toString(res.getInt("delegacion_id"))),
                                res.getFloat("salario")
                        );
                        break;
                    case "colaborador":
                        p = new Colaborador(
                                res.getInt("id"),
                                res.getString("nombre"),
                                res.getString("nif"),
                                res.getString("direccion"),
                                delegacionDao.readDelegacion(Integer.toString(res.getInt("delegacion_id"))),
                                res.getString("area_colaboracion")
                        );
                        break;
                    case "nacional":
                        p = new Voluntario_Nacional(
                                res.getInt("id"),
                                res.getString("nombre"),
                                res.getString("nif"),
                                res.getString("direccion"),
                                delegacionDao.readDelegacion(Integer.toString(res.getInt("delegacion_id"))),
                                res.getString("tarea_desempena"),
                                res.getString("tipo_voluntario"),
                                res.getString("ciudad")
                        );
                        break;
                    case "internacional":
                        p = new Voluntario_Internacional(
                                res.getInt("id"),
                                res.getString("nombre"),
                                res.getString("nif"),
                                res.getString("direccion"),
                                delegacionDao.readDelegacion(Integer.toString(res.getInt("delegacion_id"))),
                                res.getString("tarea_desempena"),
                                res.getString("tipo_voluntario"),
                                res.getString("pais")
                        );
                        break;
                }
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return p;
    }

    @Override
    public Personal updatePersonal(Personal personal) {
        try {
            String[] tipoPersonalClassSplit = personal.getClass().getName().split("\\.");
            String tipoPersonalClass = tipoPersonalClassSplit[tipoPersonalClassSplit.length - 1];

            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm;
            switch (tipoPersonalClass) {
                case "Empleado":
                    String UPDATE_EMPLEADO = "UPDATE personal SET nombre = ?, nif = ?, direccion = ?, salario = ?, delegacion_id = ? " +
                            "WHERE id = ?";
                    stm = connection.prepareStatement(UPDATE_EMPLEADO);
                    stm.setString(1, personal.getNombre());
                    stm.setString(2, personal.getNif());
                    stm.setString(3, personal.getDireccion());
                    stm.setFloat(4, ((Empleado) personal).getSalario());
                    stm.setInt(5, personal.getDelegacion().getId());
                    stm.setInt(6, personal.getIdPersona());
                    stm.executeUpdate();
                    break;
                case "Colaborador":
                    String UPDATE_COLABORADOR = "UPDATE personal SET nombre = ?, nif = ?, direccion = ?, area_colaboracion = ?, delegacion_id = ? " +
                            "WHERE id = ?";
                    stm = connection.prepareStatement(UPDATE_COLABORADOR);
                    stm.setString(1, personal.getNombre());
                    stm.setString(2, personal.getNif());
                    stm.setString(3, personal.getDireccion());
                    stm.setString(4, ((Colaborador) personal).getAreaColaboracion());
                    stm.setInt(5, personal.getDelegacion().getId());
                    stm.setInt(6, personal.getIdPersona());
                    stm.executeUpdate();
                    break;
                case "Voluntario_Nacional":
                    String UPDATE_VOLUNTARIO_NACIONAL = "UPDATE personal SET nombre = ?, nif = ?, direccion = ?, tarea_desempena = ?, ciudad = ?, delegacion_id = ? " +
                            "WHERE id = ?";
                    stm = connection.prepareStatement(UPDATE_VOLUNTARIO_NACIONAL);
                    stm.setString(1, personal.getNombre());
                    stm.setString(2, personal.getNif());
                    stm.setString(3, personal.getDireccion());
                    stm.setString(4, ((Voluntario) personal).getTareaDesepena());
                    stm.setString(5, ((Voluntario_Nacional) personal).getCiudad());
                    stm.setInt(6, personal.getDelegacion().getId());
                    stm.setInt(7, personal.getIdPersona());
                    stm.executeUpdate();
                    break;
                case "Voluntario_Internacional":
                    String CREATE_VOLUNTARIO_INTERNACIONAL = "UPDATE personal SET nombre = ?, nif = ?, direccion = ?, tarea_desempena = ?, pais = ?, delegacion_id = ? " +
                            "WHERE id = ?";
                    stm = connection.prepareStatement(CREATE_VOLUNTARIO_INTERNACIONAL);
                    stm.setString(1, personal.getNombre());
                    stm.setString(2, personal.getNif());
                    stm.setString(3, personal.getDireccion());
                    stm.setString(4, ((Voluntario) personal).getTareaDesepena());
                    stm.setString(5, ((Voluntario_Internacional) personal).getPais());
                    stm.setInt(6, personal.getDelegacion().getId());
                    stm.setInt(7, personal.getIdPersona());
                    stm.executeUpdate();
                    break;
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return personal;
    }

    @Override
    public void deletePersonal(int idPersonal) {
        try {
            String DELETE_PROYECTO_PERSONAL = "DELETE FROM proyecto_personal WHERE personal_id = ?";
            String DELETE_PERSONAL = "DELETE FROM personal WHERE id = ?";

            Connection connection = DbDonnection.connectDatabase();

            PreparedStatement stmProyectoPersonal = connection.prepareStatement(DELETE_PROYECTO_PERSONAL);
            stmProyectoPersonal.setInt(1, idPersonal);
            stmProyectoPersonal.executeUpdate();

            PreparedStatement stm = connection.prepareStatement(DELETE_PERSONAL);
            stm.setInt(1, idPersonal);
            stm.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
