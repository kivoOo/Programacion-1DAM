<%-- 
    Document   : logout
    Created on : 25 ene. 2020, 16:44:31
    Author     : carlos
--%>

<%
  session.removeAttribute("usuario");
  response.sendRedirect("index.html");
%>
