package com.mycompany.mental_health.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private static final String URL = "jdbc:mysql://localhost:3306/salud_mental";
    private static final String USER = "root"; // Reemplaza con tu usuario de MySQL
    private static final String PASSWORD = "Samufut322%"; // Reemplaza con tu contraseña

    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
