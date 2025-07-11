package com.mycompany.mental_health.control;

import java.io.IOException;

import com.mycompany.mental_health.dao.UsuarioDAO;
import com.mycompany.mental_health.model.Usuario;
import com.mycompany.mental_health.util.DB;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String accion = request.getParameter("accion");
        UsuarioDAO dao = new UsuarioDAO(DB.getConexion());

        if ("registrar".equals(accion)) {
            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");

            Usuario u = new Usuario(nombre, correo, contrasena);
            dao.registrar(u);

            // Redirige al formulario con mensaje de éxito
            response.sendRedirect("registroUsuario.html?registro=ok");
            return;
        }

        if ("eliminar".equals(accion)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean eliminado = dao.eliminar(id);

                // Redirige con confirmación del resultado
                response.sendRedirect("gestionarUsuario.jsp?resultado=" + (eliminado ? "ok" : "error"));
                return;

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("gestionarUsuario.jsp?resultado=error");
                return;
            }
        }

        // Si no se reconoce la acción
        response.sendRedirect("index.html");
    }
}