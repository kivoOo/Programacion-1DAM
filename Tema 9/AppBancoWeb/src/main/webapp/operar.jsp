<%-- 
    Document   : operar
    Created on : 12 feb. 2020, 0:16:05
    Author     : carlos
--%>

<%@page import="modelo.Cliente"%>
<%@page import="modelo.GestionCuentas"%>
<%
    GestionCuentas gestion = null;
    Long cuenta = 0l;
    boolean exito = false;
    try {
        gestion = new GestionCuentas(); //Recupero el listado de cuentas de disco
    } catch (Exception e) {
        session.setAttribute("error", "Fallo al recuperar el estado del sistema");
        response.sendRedirect("error.jsp");
    }
    if (gestion != null) {
        String operacion = request.getParameter("operacion");

        if (operacion.equals("sacardinero")) { //Sacar dinero
            String num = request.getParameter("numcuenta");
            Long numCuenta = Long.parseLong(num);
            String cant = request.getParameter("cantidad");
            Long cantidad = Long.parseLong(cant);
            if (gestion.buscarCuenta(numCuenta) != -1) {
                gestion.getCuenta(gestion.buscarCuenta(numCuenta)).reintegro(cantidad);
                gestion.SalvarCuentas();//Guardo el estado
                exito = true;
                session.setAttribute("exito", "La operación de sacar dinero se ha realizado");
                response.sendRedirect("realizada.jsp");
            }
        } // El if de sacardinero

        if (operacion.equals("ingresardinero")) { //Sacar dinero
            String num = request.getParameter("numcuenta");
            Long numCuenta = Long.parseLong(num);
            String cant = request.getParameter("cantidad");
            Long cantidad = Long.parseLong(cant);
            if (gestion.buscarCuenta(numCuenta) != -1) {
                gestion.getCuenta(gestion.buscarCuenta(numCuenta)).ingreso(cantidad);
                gestion.SalvarCuentas();//Guardo el estado
                exito = true;
                session.setAttribute("exito", "La operación de ingresar dinero se ha realizado");
                response.sendRedirect("realizada.jsp");
            }
        } // El if de ingresar dinero

        if (operacion.equals("traspasardinero")) { //Sacar dinero
            String num1 = request.getParameter("numcuenta1");
            Long numCuenta1 = Long.parseLong(num1);
            String num2 = request.getParameter("numcuenta2");
            Long numCuenta2 = Long.parseLong(num2);
            String cant = request.getParameter("cantidad");
            Long cantidad = Long.parseLong(cant);
            if (gestion.buscarCuenta(numCuenta1) != -1 && gestion.buscarCuenta(numCuenta2) != -1) {
                gestion.getCuenta(gestion.buscarCuenta(numCuenta1)).reintegro(cantidad);
                gestion.getCuenta(gestion.buscarCuenta(numCuenta2)).ingreso(cantidad);
                gestion.SalvarCuentas();//Guardo el estado
                exito = true;
                session.setAttribute("exito", "La operación de traspasar dinero se ha realizado");
                response.sendRedirect("realizada.jsp");
            }
        } // El if de traspasar dinero

        if (operacion.equals("bizum")) { //Sacar dinero
            String num1 = request.getParameter("nummovil1");
            String num2 = request.getParameter("nummovil2");
            String cant = request.getParameter("cantidad");
            Long cantidad = Long.parseLong(cant);
            int posOrigen, posDestino;
            posOrigen = gestion.buscarCuentaMovil(num1);
            posDestino = gestion.buscarCuentaMovil(num2);
            if (posOrigen != -1 && posDestino != -1) {
                gestion.getCuenta(posOrigen).reintegro(cantidad);
                gestion.getCuenta(posDestino).ingreso(cantidad);
                gestion.SalvarCuentas();//Guardo el estado
                exito = true;
                session.setAttribute("exito", "La operación de traspasar dinero se ha realizado");
                response.sendRedirect("realizada.jsp");
            }
            
        } // El if de bizum dinero
        
        if (operacion.equals("libre")) { //Esta operación está libre
            
            
        } // El if de libre

    } //El if de gestion

    if (!exito) {
        session.setAttribute("error", "La operación no se pudo realizar, compruebe los datos");
        response.sendRedirect("error.jsp");
    }

%>