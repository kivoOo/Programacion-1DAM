<%--
    Document   : mostrarcliente
    Created on : 27 ene. 2020, 10:52:04
    Author     : carlos
--%>

<%@page import="modelo.Usuario"%>
<%@page import="modelo.Cliente"%>
<%@page import="modelo.GestionCuentas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Cliente info</title>
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
        Cliente c = null;
        boolean pintoCliente = false;
        boolean pintoClientes = false;
        try {
            gestion = new GestionCuentas(); //Recupero el listado de cuentas de disco
        } catch (Exception e) {
            session.setAttribute("error", "Fallo al recuperar el estado del sistema");
            response.sendRedirect("error.jsp");
        }
        if (gestion != null) {
            boolean encontrado = false;
            String dni = (String) session.getAttribute("dni_busqueda");
            //String dni = request.getParameter("dni");
            if (dni != null) {
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
                System.out.println("Aquí ha terminado la comprobacion de DNI");
                if (!valido) {
                    session.setAttribute("error", "El DNI no es válido");
                    response.sendRedirect("error.jsp");
                } else {
                    c = gestion.buscarCliente(dni);
                    if (c == null) {
                        session.setAttribute("error", "El cliente no pertenece a este banco");
                        response.sendRedirect("error.jsp");
                    } else {
                        pintoCliente = true;
                    }

                }
            }

        }


    %>

    <%        if (pintoCliente) {

    %>
    <br>
    <br>
    <h2> Perfil del cliente <%= c.getDni()%></h2>
    <form action = "mostrarcuentas.jsp">
        <div class="form-group row">
            <label for="dni" class="col-sm-2 col-form-label">DNI:</label>
            <div class="col-sm-10">
                <input type="text" name="dni" readonly class="form-control" id="staticEmail" value=<%=c.getDni()%>>

            </div>
            <label for="nombre" class="col-sm-2 col-form-label">Nombre:</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control" id="staticEmail" value=<%=c.getNombre()%>>

            </div>
            <label for="apellidos" class="col-sm-2 col-form-label">Apellidos:</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control" id="staticEmail" value=<%=c.getApellidos()%>>

            </div>
            <label for="movil" class="col-sm-2 col-form-label">Móvil:</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control" id="staticEmail" value=<%=c.getNumMovil()%>>

            </div>
            <label for="email" class="col-sm-2 col-form-label">Mail:</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control" id="staticEmail" value=<%=c.getEmail()%>>

            </div>
            <button type="submit" class="btn btn-primary">Ver Cuentas</button>
        </div>

    </form>

</div>
    <%
        }
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
