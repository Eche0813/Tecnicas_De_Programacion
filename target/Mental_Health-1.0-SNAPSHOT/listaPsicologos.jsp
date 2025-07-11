<%@ page import="java.util.*, com.mycompany.mental_health.dao.PsicologoDAO, com.mycompany.mental_health.model.Psicologo, com.mycompany.mental_health.util.DB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Psicólogos</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #eef6f9;
            margin: 0;
            color: #333;
        }
        h2 {
            text-align: center;
            margin-top: 30px;
        }
        table {
            width: 80%;
            margin: 30px auto;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        th {
            background-color: #0077b6;
            color: white;
            padding: 12px;
        }
        td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .mensaje {
            text-align: center;
            margin-top: 50px;
            font-size: 1.2rem;
            color: #555;
        }
    </style>
</head>
<body>

<h2>Directorio de Psicólogos Registrados</h2>

<%
    PsicologoDAO dao = new PsicologoDAO(DB.getConexion());
    List<Psicologo> psicologos = dao.obtenerTodos();
%>

<% if (psicologos.isEmpty()) { %>
    <p class="mensaje">No hay psicólogos registrados en este momento. Por favor, vuelve más tarde.</p>
<% } else { %>
    <table>
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Especialidad</th>
            <th>Correo</th>
        </tr>
        <% for (Psicologo p : psicologos) { %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getNombre() %></td>
                <td><%= p.getEspecialidad() != null ? p.getEspecialidad() : "No especificada" %></td>
                <td><%= p.getCorreo() %></td>
            </tr>
        <% } %>
    </table>
<% } %>

</body>
</html>