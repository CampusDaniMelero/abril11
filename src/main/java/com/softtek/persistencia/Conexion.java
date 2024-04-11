package com.softtek.persistencia;

import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {
    protected Connection miConexion;

    public void abrirConexion() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        miConexion = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/northwind", "postgres", "root");

    }

    public Conexion() {

    }


    //Getters y Setters

    public Connection getMiConexion() {
        return miConexion;
    }

    public void setMiConexion(Connection miConexion) {
        this.miConexion = miConexion;
    }






    public void cerrarConexion() {
        try {
            if (miConexion != null) {
                miConexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



