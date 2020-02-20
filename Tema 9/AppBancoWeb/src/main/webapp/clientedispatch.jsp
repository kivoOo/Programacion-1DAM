<%-- 
    Document   : clientedispatch
    Created on : 11 feb. 2020, 13:10:04
    Author     : carlos
--%>

<%
    String dni = request.getParameter("dni");
    String apel = request.getParameter("apellidos");
    String numCuenta = request.getParameter("numcuenta");
    
    System.out.println("dni = " + dni);
    System.out.println("apel = " + apel);
    System.out.println("numCuenta = " + numCuenta);
    
    if (dni != "" && dni != null) {
        session.setAttribute("dni_busqueda", dni);
        response.sendRedirect("mostrarcliente.jsp");
    }else if (apel != "" && apel != null){
        session.setAttribute("apellidos_busqueda", apel);
        response.sendRedirect("busca_usuarios.jsp");
    }else if (numCuenta != "" && numCuenta != null){
        session.setAttribute("cuenta_busqueda", numCuenta);
        response.sendRedirect("mostrarcuenta.jsp");
    }
    else{
        session.setAttribute("error", "Términos de búsqueda incorrectos");
        response.sendRedirect("error.jsp");
    }
    
%>