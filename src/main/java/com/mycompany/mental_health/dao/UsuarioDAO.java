package com.mycompany.mental_health.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.mental_health.model.Usuario;

public class UsuarioDAO {

    private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    //  Registrar nuevo usuario
    public boolean registrar(Usuario u) {
        try {
            String sql = "INSERT INTO usuarios (nombre, correo, contrasena) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getContrasena());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //  Eliminar usuario por ID
    public boolean eliminar(int id) {
        try {
            String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //  Obtener todos los usuarios registrados
public List<Usuario> obtenerTodos() {
    List<Usuario> lista = new ArrayList<>();
    try {
        String sql = "SELECT * FROM usuarios";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            // Validación segura
            int id = rs.getInt("id_usuario");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            String contrasena = rs.getString("contrasena");

            if (nombre != null && correo != null && contrasena != null) {
                Usuario u = new Usuario(id, nombre, correo, contrasena);
                lista.add(u);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}

}
