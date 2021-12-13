package com.P5.DAO;

import com.P5.DAO.interfaces.IDelegacion;
import com.P5.entities.Delegacion;
import com.P5.utils.DbDonnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DelegacionDBImpl implements IDelegacion {

    public DelegacionDBImpl() throws SQLException {
        String CREATE_TABLE_DELEGACION = "CREATE TABLE IF NOT EXISTS delegaciones ("
                + "id int(11) AUTO_INCREMENT,"
                + "ciudad VARCHAR(255) NOT NULL,"
                + "direccion VARCHAR(255) NOT NULL,"
                + "telefono VARCHAR(12) NOT NULL,"
                + "email VARCHAR(255) NOT NULL,"
                + "central TINYINT(1) NOT NULL DEFAULT 0,"
                + "PRIMARY KEY (id))";
        Connection connection = DbDonnection.connectDatabase();
        PreparedStatement stm = connection.prepareStatement(CREATE_TABLE_DELEGACION);
        stm.executeUpdate();
        connection.close();
    }

    @Override
    public List<Delegacion> findAllDelegacion() {
        List<Delegacion> delegacionesList = new ArrayList<>();

        try {
            String FIND_ALL_DELEGACION = "SELECT * FROM delegaciones";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm = connection.prepareStatement(FIND_ALL_DELEGACION);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                Delegacion d = new Delegacion(
                        res.getInt("id"),
                        res.getString("ciudad"),
                        res.getString("direccion"),
                        res.getString("telefono"),
                        res.getString("email"),
                        res.getBoolean("central")
                );
                delegacionesList.add(d);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return delegacionesList;
    }

    @Override
    public void createDelegacion(Delegacion delegacion) {
        try {
            String CREATE_DELEGACION = "INSERT INTO delegaciones VALUES (null, ?, ?, ?, ?, ?)";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm = connection.prepareStatement(CREATE_DELEGACION);
            stm.setString(1, delegacion.getCiudad());
            stm.setString(2, delegacion.getDireccion());
            stm.setString(3, delegacion.getTelefono());
            stm.setString(4, delegacion.getEmail());
            stm.setBoolean(5, delegacion.getCentral());
            stm.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Delegacion readDelegacion(String id) {
        Delegacion delegacion = null;

        try {
            String FIND_DELEGACION = "SELECT * FROM delegaciones WHERE id = ?";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm = connection.prepareStatement(FIND_DELEGACION);
            stm.setInt(1, Integer.parseInt(id));
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                delegacion = new Delegacion(
                        res.getInt("id"),
                        res.getString("ciudad"),
                        res.getString("direccion"),
                        res.getString("telefono"),
                        res.getString("email"),
                        res.getBoolean("central")
                );
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return delegacion;
    }

    @Override
    public Delegacion updateDelegacion(Delegacion delegacion) {
        try {
            String UPDATE_DELEGACION = "UPDATE delegaciones SET " +
                    "ciudad = ?, " +
                    "direccion = ?, " +
                    "telefono = ?, " +
                    "email = ?, " +
                    "central = ? " +
                    "WHERE id = ?";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm = connection.prepareStatement(UPDATE_DELEGACION);
            stm.setString(1, delegacion.getCiudad());
            stm.setString(2, delegacion.getDireccion());
            stm.setString(3, delegacion.getTelefono());
            stm.setString(4, delegacion.getEmail());
            stm.setBoolean(5, delegacion.getCentral());
            stm.setInt(6, delegacion.getId());
            stm.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return delegacion;
    }

    @Override
    public void deleteDelegacion(String id) {
        try {
            String DELETE_DELEGACION = "DELETE FROM delegaciones WHERE id = ?";
            Connection connection = DbDonnection.connectDatabase();
            PreparedStatement stm = connection.prepareStatement(DELETE_DELEGACION);
            stm.setInt(1, Integer.parseInt(id));
            stm.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
