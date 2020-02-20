<%-- 
    Document   : menuoperaciones
    Created on : 11 feb. 2020, 23:52:58
    Author     : carlos
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Menú de operaciones entre cuentas</title>
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
    

  <!-- TECNOLOGIAS -->
<section class="tecnologias contenedor seccion" id="tecnologias">
  <h2>APP Banco Web</h2>
  <p>OPERACIONES ENTRE CUENTAS</p>
  <ul class="lista_tecnologias clearfix">
    <li>
      <div class="tecnologia">
          <a href="sacardinero.jsp"><img src="images/sacar.png" alt="img_crear"></a>
        <p>Sacar dinero</p>
      </div>
    </li>
    <li>
        <div class="tecnologia">
          <a href="ingresardinero.jsp"><img src="images/ingresar.png" alt="img_delete "></a>
          <p>Ingresar dinero</p>
        </div>
    </li>
    <li>
        <div class="tecnologia">
          <a href="traspasardinero.jsp"><img src="images/traspaso.png" alt="img_cliente"></a>
          <p>Traspaso entre cuentas</p>
        </div>
    </li>
    <li>
        <div class="tecnologia">
          <a href="bizum.jsp"><img src="images/bizum.png" alt="img_operate"></a>
          <p>Bizum</p>
        </div>
    </li>
    <li>
        <div class="tecnologia">
          <a href="main.jsp"><img src="images/libre.png" alt="img_red"></a>
          <p>Libre</p>
        </div>
    </li>
    <li>
        <div class="tecnologia">
          <a href="main.jsp"><img src="images/exit.png" alt="img_exit"></a>
          <p>Volver</p>
        </div>
    </li>
  </ul>
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
