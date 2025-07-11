package com.mycompany.mental_health.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.mental_health.model.Psicologo;

public class PsicologoDAO {
    private Connection con;

    public PsicologoDAO(Connection con) {
        this.con = con;
    }

    public boolean registrar(Psicologo p) {
        String sql = "INSERT INTO psicologos (nombre, correo, contrasena, especialidad) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getContrasena());
            ps.setString(4, p.getEspecialidad());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar psicólogo: " + e.getMessage());
            return false;
        }
    }

    public Psicologo login(String correo, String contrasena) {
        String sql = "SELECT * FROM psicologos WHERE correo = ? AND contrasena = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Psicologo p = new Psicologo();
                p.setId(rs.getInt("id_psicologo"));
                p.setNombre(rs.getString("nombre"));
                p.setCorreo(rs.getString("correo"));
                p.setEspecialidad(rs.getString("especialidad"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println("Error en login psicólogo: " + e.getMessage());
        }
        return null;
    }
    public List<Psicologo> obtenerTodos() {
    List<Psicologo> lista = new ArrayList<>();
    try {
        String sql = "SELECT * FROM psicologos";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Psicologo p = new Psicologo();
            p.setId(rs.getInt("id_psicologo")); // ajusta si tienes otro nombre de columna
            p.setNombre(rs.getString("nombre"));
            p.setCorreo(rs.getString("correo"));
            p.setContrasena(rs.getString("contrasena"));
            p.setEspecialidad(rs.getString("especialidad"));

            lista.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}

}
