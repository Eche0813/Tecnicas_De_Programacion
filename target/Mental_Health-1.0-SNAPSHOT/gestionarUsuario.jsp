<%@ page import="java.util.*, com.mycompany.mental_health.dao.UsuarioDAO, com.mycompany.mental_health.model.Usuario, com.mycompany.mental_health.util.DB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String acceso = request.getParameter("acceso");
    boolean autenticado = false;

    if ("validar".equals(acceso)) {
        String nombreInput = request.getParameter("nombre");
        String passInput = request.getParameter("contrasena");
        if ("admin".equals(nombreInput) && "admin123".equals(passInput)) {
            autenticado = true;
        }
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Usuarios</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f3f7fb;
            margin: 0;
            color: #333;
        }
        header {
            background: linear-gradient(90deg, #00b4d8, #0077b6);
            color: white;
            padding: 20px;
            text-align: center;
        }
        main {
            max-width: 900px;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 12px rgba(0,0,0,0.1);
        }
        h2 {
            margin-top: 0;
        }
        form input {
            padding: 10px;
            margin: 5px 10px 20px 0;
            border: 2px solid #ccc;
            border-radius: 6px;
        }
        form input[type="submit"] {
            background-color: #0077b6;
            color: white;
            border: none;
            cursor: pointer;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th {
            background-color: #0077b6;
            color: white;
            padding: 12px;
        }
        td {
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        form.inline {
            display: inline;
        }
        form.inline button {
            padding: 6px 10px;
            background-color: #00b894;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        form.inline button.delete {
            background-color: #d63031;
        }
        .alert {
    padding: 12px 20px;
    margin-bottom: 20px;
    border-radius: 6px;
    font-weight: bold;
    text-align: center;
}

.alert.success {
    background-color: #d4edda;
    color: #155724;
    border: 1px solid #c3e6cb;
}

.alert.error {
    background-color: #f8d7da;
    color: #721c24;
    border: 1px solid #f5c6cb;
}
    </style>
</head>
<body>

<header>
    <h1>Gestión de Usuarios</h1>
</header>

<main>
    <%
    String resultado = request.getParameter("resultado");
    if ("ok".equals(resultado)) {
%>
    <div class="alert success">✅ Usuario eliminado correctamente.</div>
<%
    } else if ("error".equals(resultado)) {
%>
    <div class="alert error">❌ Ocurrió un error al eliminar el usuario.</div>
<%
    }
%>

    <% if (!autenticado) { %>
        <h2>Acceso restringido</h2>
        <form method="post" action="gestionarUsuario.jsp">
            <input type="hidden" name="acceso" value="validar">
            <label for="nombre">Usuario:</label>
            <input type="text" name="nombre" id="nombre" required>
            <label for="contrasena">Contraseña:</label>
            <input type="password" name="contrasena" id="contrasena" required>
            <input type="submit" value="Entrar">
        </form>
    <% } else { 
        UsuarioDAO dao = new UsuarioDAO(DB.getConexion());
        List<Usuario> usuarios = dao.obtenerTodos();
    %>
    <p>Usuarios encontrados: <%= usuarios.size() %></p>

        <h2>Usuarios registrados</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Acciones</th>
            </tr>
            <% for (Usuario u : usuarios) { %>
                <tr>
                    <td><%= u.getId() %></td>
                    <td><%= u.getNombre() %></td>
                    <td><%= u.getCorreo() %></td>
                    <td>
                        <form class="inline" method="post" action="adminUsuario" onsubmit="return confirm('¿Eliminar este usuario?');">
                            <input type="hidden" name="accion" value="eliminar">
                            <input type="hidden" name="id" value="<%= u.getId() %>">
                            <button type="submit" class="delete">Eliminar</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </table>

    <% } %>
</main>

</body>
</html>
