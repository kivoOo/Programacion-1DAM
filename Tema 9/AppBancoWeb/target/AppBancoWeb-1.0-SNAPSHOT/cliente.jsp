<%-- 
    Document   : cliente
    Created on : 26 ene. 2020, 18:25:32
    Author     : carlos
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Consulta de datos</title>
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

    <!-- Acerca de mi-->

    <section id="sobremi" class="seccion contenedor">
        <h2>Búsqueda de información</h2>
        <br>
        <div class="sobremi clearfix">  
            <ul>
                <li>Si introduce el DNI de un cliente, le mostraremos su información</li>
                <li>Si introduce apellidos, le mostraremos una lista de clientes coincidente</li>
            </ul>
            <br>
            <br>
            <div class="container">
                <form class="form-inline" action="clientedispatch.jsp">
                    <label class="sr-only" for="inlineFormInputName2">DNI</label>
                    <input type="text" class="form-control mb-2 mr-sm-2" name="dni" id="inlineFormInputName2" placeholder="DNI">

                    <label class="sr-only" for="inlineFormInputGroupUsername2">Apellidos</label>
                    <div class="input-group mb-2 mr-sm-2">
                        <div class="input-group-prepend">
                            <div class="input-group-text">-</div>
                        </div>
                        <input type="text" class="form-control" name="apellidos" id="inlineFormInputGroupUsername2" placeholder="Apellidos">
                    </div>


                    <button type="submit" class="btn btn-primary mb-2">Buscar</button>
                </form>
            </div>
            <br>
            <br>
            <ul>
                <li>Si introduce un número de cuenta, le mostraremos su información</li>
            </ul>
            <br>
            <br>
            <div class="container">
                <form class="form-inline" action="clientedispatch.jsp">
                    <label class="sr-only" for="inlineFormInputName2">Num Cuenta</label>
                    <input type="text" class="form-control mb-2 mr-sm-2" name="numcuenta" id="inlineFormInputName2" placeholder="Num cuenta">
                    <button type="submit" class="btn btn-primary mb-2">Buscar</button>
                </form>
            </div>
        </div>
    </section>

    <!-- Fin Acerca de mi-->



    <!-- FIN HEADER -->

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


