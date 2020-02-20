<%-- 
    Document   : borracuenta
    Created on : 27 ene. 2020, 0:13:51
    Author     : carlos
--%>


<%@page import="modelo.Cliente"%>
<%@page import="modelo.GestionCuentas"%>
<%
    GestionCuentas gestion = null;
    Long cuenta = 0l;
    boolean encontrado = false;
    try {
        gestion = new GestionCuentas(); //Recupero el listado de cuentas de disco
    } catch (Exception e) {
        session.setAttribute("error", "Fallo al recuperar el estado del sistema");
        response.sendRedirect("error.jsp");
    }
    if (gestion != null) {
        String dni = request.getParameter("dni");
        String inte = request.getParameter("cuenta");
        try {
            cuenta = Long.parseLong(inte);
        } catch (Exception e) {
            session.setAttribute("error", "Fallo en los datos introducidos");
            response.sendRedirect("error.jsp");
        }
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
        }else {
            if (!gestion.borrarCuenta(cuenta) || (gestion.buscarCliente(dni) == null)) {
                session.setAttribute("error", "La cuenta o el DNI no son válidos");
                response.sendRedirect("error.jsp");
            }else {
                gestion.SalvarCuentas();//Guardo el estado
                response.sendRedirect("main.jsp");
            }
            
        }
    }
%>