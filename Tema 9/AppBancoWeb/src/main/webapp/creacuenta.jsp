<%-- 
    Document   : creacuenta
    Created on : 26 ene. 2020, 13:14:01
    Author     : carlos
--%>

<%@page import="modelo.Cliente"%>
<%@page import="modelo.GestionCuentas"%>
<%
    GestionCuentas gestion = null;
    Float interes = 0f;
    try {
        gestion = new GestionCuentas(); //Recupero el listado de cuentas de disco
    } catch (Exception e) {
        session.setAttribute("error", "Fallo al recuperar el estado del sistema");
        response.sendRedirect("error.jsp");
    }
    if (gestion != null) {     
        boolean encontrado = false;
        String dni = request.getParameter("dni");
        String inte = request.getParameter("interes");
        try {
            interes = Float.parseFloat(inte);
        } catch (Exception e) {
            session.setAttribute("error", "Fallo en los datos introducidos");
            response.sendRedirect("error.jsp");
        }
        System.out.println("Aquí hemos cogido los parametros y parseados");
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
            Cliente c = gestion.buscarCliente(dni);
            if (c == null) {
                session.setAttribute("error", "El cliente no pertenece a este banco");
                response.sendRedirect("error.jsp");
            }else {
                
                long numCuenta  = gestion.añadirCuenta(c, interes);
                gestion.SalvarCuentas();//Guardo el estado
                session.setAttribute("exito", "La operación de creación de cuenta se ha realizado. Cuenta número: " + numCuenta);
                response.sendRedirect("realizada.jsp");
            }
            
        }
    }


%>