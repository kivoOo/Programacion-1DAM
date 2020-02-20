<%-- 
    Document   : mostrarcuenta
    Created on : 12 feb. 2020, 10:47:18
    Author     : carlos
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Cuenta"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Cliente"%>
<%@page import="modelo.GestionCuentas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Cuenta info</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="manifest" href="site.webmanifest">
        <link rel="apple-touch-icon" href="icon.png">
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!-- Place favicon.ico in the root directory -->

        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="stylesheet" href="css/all.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans|Oswald|PT+Sans" rel="stylesheet">
    </head>


    <body>
        <%
            if (session.getAttribute("usuario") != null) {
                Usuario usuario = (Usuario) session.getAttribute("usuario");

        %>

        <!-- INICIO HEADER -->
        <header class="site_header">
            <div class="hero" id="inicio">
                <div class="contenido_header">
                    <div class="barra_sup clearfix">
                        <div class="logo">
                            <img src="images/logo_cp.gif" alt="logo_site">
                        </div>
                        <nav class="navegacion_principal clearfix">
                            <a href="#inicio">Bienvenido: <%=usuario%></a>
                        </nav>
                    </div>
                </div>
            </div> <!--DIV CONTENIDO_HERO-->
        </div> <!--DIV HERO-->
    </header>
    <!-- FIN HEADER -->


    <div class="container">
        <%
            GestionCuentas gestion = null;
            Cuenta c = null;
            boolean pintoCuenta = false;
            try {
                gestion = new GestionCuentas(); //Recupero el listado de cuentas de disco
            } catch (Exception e) {
                session.setAttribute("error", "Fallo al recuperar el estado del sistema");
                response.sendRedirect("error.jsp");
            }
            if (gestion != null) {
                String num = (String) session.getAttribute("cuenta_busqueda");
                Long numCuenta = Long.parseLong(num);
                boolean encontrado = false;
                int pos;
                pos = gestion.buscarCuenta(numCuenta);
                if (pos != -1) {
                    c = gestion.getCuenta(pos);
                    pintoCuenta = true;
                } else {
                    session.setAttribute("error", "La cuenta no pertenece a este banco");
                    response.sendRedirect("error.jsp");

                }
            }
        %>

        <%        if (pintoCuenta) {

        %>
        <br>
        <br>
               
        <h2> Datos de la cuenta <%= c.getNumero()%></h2>
        <form action = "main.jsp">
            <div class="form-group row">
                <label for="dni" class="col-sm-2 col-form-label">Número:</label>
                <div class="col-sm-10">
                    <input type="text" name="dni" readonly class="form-control" id="staticEmail" value=<%=c.getNumero()%>>

                </div>
                <label for="nombre" class="col-sm-2 col-form-label">Titular:</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" id="staticEmail" value=<%=c.getTitular().getNombre()%>>

                </div>
                <label for="apellidos" class="col-sm-2 col-form-label">Saldo:</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" id="staticEmail" value=<%=c.getSaldo()%>>

                </div>
                <label for="movil" class="col-sm-2 col-form-label">Interés:</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" id="staticEmail" value=<%=c.getInteresAnual()%>>

                </div>          
            </div>

        </form>

        <div class="panel panel-primary">
            <div class="panel-heading text-center"><h1>Últimos movimientos</h1></div>
            <table class="table table-striped">
                <tr><th>Fecha</th><th>Tipo</th><th>Importe</th><th>Saldo</th></tr>

                <%
                    ArrayList listaMovimientos;
                    listaMovimientos = c.getMovimientos();
                    for (Object movimiento : listaMovimientos) {
                        Cuenta.Movimiento temp = (Cuenta.Movimiento) movimiento;
                        out.println("<tr><td>");
                        String fechaFormateada = new SimpleDateFormat("dd-MM-yyyy").format(temp.getFecha());
                        out.println(fechaFormateada + "</td>");
                        out.println("<td>" + temp.getTipo() + "</td>");
                        out.println("<td>" + temp.getImporte() + "</td>");
                        out.println("<td>" + temp.getSaldo() + "</td>");         
                        }

                %>
            </table>
            <form method="get" action="main.jsp">
                <button type="submit" value="Volver" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Volver</button>           
            </form>
        </div>


    </div>


    <%        }
    %>
    <!-- FOOTER -->
    <footer class="site_footer">

        <div class="menu">

            <nav class="redes_sociales">
                <a href="main.jsp"><i class="far fa-envelope-open"></i></a>
                <a href="https://t.me/carlosbarroso"><i class="fab fa-telegram-plane"></i></a>
                <a href="https://twitter.com/carlos_profe_"><i class="fab fa-twitter"></i></a>
                <a href="https://github.com/carlosprofe6"><i class="fab fa-github-square"></i></a>
            </nav>
        </div>
    </div>

    <p class="copy">Todos los derechos reservados &copy;</p>




</footer>



<!-- FIN FOOTER -->

<script src="js/vendor/modernizr-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery-3.3.1.min.js"><\/script>')</script>
<script src="js/plugins.js"></script>
<script src="js/main.js"></script>

<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->


<% } else { //Cuando no existe la sesión
        session.setAttribute("error", "No está logueado");
        response.sendRedirect("error.jsp");
    }%>
</body>
</html>
