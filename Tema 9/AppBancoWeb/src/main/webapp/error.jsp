
<%@page import="modelo.Usuario"%>
<%-- 
    Document   : menuoperaciones
    Created on : 11 feb. 2020, 23:52:58
    Author     : carlos
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Error!</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="manifest" href="site.webmanifest">
        <link rel="apple-touch-icon" href="icon.png">
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!-- Place favicon.ico in the root directory -->

        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="stylesheet" href="css/all.min.css">
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



    <section class="tecnologias contenedor seccion" id="tecnologias">
        <h2>Ha ocurrido un error al realizar su petición</h2>
        <p> 
            <%=session.getAttribute("error")%>
            <% session.removeAttribute("error");%>
            <br>
            <a href="login.jsp">Pulse aquí para continuar</a>
        </p>

    </section>




    <!-- FIN TECNOLOGIAS -->


    <footer class="site_footer">

        <div class="menu">

            <nav class="redes_sociales">  
                <a href="mailto: admin@carlosprofe.es"><i class="far fa-envelope-open"></i></a>
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

<% } else { //Cuando no existe la sesión
        session.setAttribute("error", "No está logueado");
        response.sendRedirect("error.jsp");
    }%>
</body>
</html>

