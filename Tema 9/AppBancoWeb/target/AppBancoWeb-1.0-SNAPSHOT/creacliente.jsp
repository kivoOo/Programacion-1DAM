<%-- 
    Document   : creacliente
    Created on : 12 feb. 2020, 13:37:30
    Author     : carlos
--%>

<%@page import="java.util.Date"%>
<%-- 
    Document   : creacuenta
    Created on : 26 ene. 2020, 13:14:01
    Author     : carlos
--%>

<%@page import="modelo.Cliente"%>
<%@page import="modelo.GestionCuentas"%>

<%
    GestionCuentas gestion = null;
    try {
        gestion = new GestionCuentas(); //Recupero el listado de cuentas de disco
    } catch (Exception e) {
        session.setAttribute("error", "Fallo al recuperar el estado del sistema");
        response.sendRedirect("error.jsp");
    }
    if (gestion != null) {
        boolean encontrado = false;
        String nombre, apellidos, numMovil, email;
        float interes = 0;
        Date fecha = new Date();
        String dni = request.getParameter("dni");
        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apel");
        numMovil = request.getParameter("movil");
        email = request.getParameter("email");
        try {
            interes = Float.parseFloat(request.getParameter("interes"));
        } catch (Exception e) {
            session.setAttribute("error", "El interés no fue introducido correctamente");
            response.sendRedirect("error.jsp");
        }

        //fecha = request.getParameter("fecha");
        char letra;
        int num, resto;
        boolean valido = false;
        String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";
        //Compruebo si longitud es 9 y si el ultimo caracter es una letra
        if (dni.length() == 9 && Character.isLetter(dni.charAt(8)) == true) {
            letra = dni.charAt(8);
            num = Integer.parseInt(dni.substring(0, 8));
            resto = num % 23;
            if (letra == LETRAS.charAt(resto)) {
                valido = true;
            }
        }
        if (!valido) {
            session.setAttribute("error", "El DNI no es válido");
            response.sendRedirect("error.jsp");
        } else {
            Cliente c = new Cliente(nombre, apellidos, dni, numMovil, email, fecha);
            long numCuenta = gestion.añadirCuenta(c, interes);
            gestion.SalvarCuentas();//Guardo el estado
            session.setAttribute("exito", "La operación de creación de cuenta se ha realizado. Cuenta número: " + numCuenta);
            response.sendRedirect("realizada.jsp");
        }

    }


%>