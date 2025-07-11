package com.mycompany.mental_health.control;


import java.io.IOException;
import java.sql.Connection;

import com.mycompany.mental_health.dao.PsicologoDAO;
import com.mycompany.mental_health.model.Psicologo;
import com.mycompany.mental_health.util.DB;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminPsicologo extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String especialidad = request.getParameter("especialidad");

        Psicologo p = new Psicologo();
        p.setNombre(nombre);
        p.setCorreo(correo);
        p.setContrasena(contrasena);
        p.setEspecialidad(especialidad);

        Connection con = DB.getConexion();
        PsicologoDAO dao = new PsicologoDAO(con);

        if (dao.registrar(p)) {
            response.sendRedirect("registroPsicologo.html?registro=ok");
        } else {
            response.sendRedirect("registroPsicologo.html?error=1");
        }

        try { con.close(); } catch (Exception e) { e.printStackTrace(); }
    }
}
