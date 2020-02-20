<%-- 
    Document   : login
    Created on : 25 ene. 2020, 16:21:08
    Author     : carlos
--%>


<%@page import="modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.DAOUsuarioSQL"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="DAO.DAOManager"%>
<%
    boolean encontrado = false;
    String user = request.getParameter("usuario");
    String clave = request.getParameter("clave");
    DAOManager dao = null;
    try {
        dao = DAOManager.getSinglentonInstance();
        dao.open();
    } catch (Exception e) {
        session.setAttribute("error", "Fallo al conectar a la Base de Datos");
        response.sendRedirect("error.jsp");
    }
    if (dao != null) {
        DAOUsuarioSQL daoUsuarioSQL = new DAOUsuarioSQL();
        ArrayList lista = daoUsuarioSQL.readAll(dao);
        for (Object u : lista) {
            Usuario temp = (Usuario) u;
            if (temp.valida(user, clave)) {
                encontrado = true;
                dao.close();
                session.setAttribute("usuario", temp);
                // Aquí podríamos recuperar de disco GestionCuentas y meterlo en la sesión
                // pero no lo haremos para leer y escribir continuamente de disco en cada
                // pantalla y evitar las perdidas de informacion
                // En caso de que nuestros datos estuvieran en BBDD Mysql si que podríamos meter
                // el DAO en la sesión
                response.sendRedirect("main.jsp");
            }
        }
        if (!encontrado) {
            dao.close();
            session.setAttribute("error", "Usuario o contraseña incorrecto");
            response.sendRedirect("index.jsp");
        }
    }


%>

