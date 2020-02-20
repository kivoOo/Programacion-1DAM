<%-- 
    Document   : clientedispatch
    Created on : 11 feb. 2020, 13:10:04
    Author     : carlos
--%>

<%

    String numCuenta = request.getParameter("numcuenta");

    System.out.println("numCuenta = " + numCuenta);

    session.setAttribute("cuenta_busqueda", numCuenta);
    response.sendRedirect("mostrarcuenta.jsp");


%>
