import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Gson gson = new Gson();
        var s = new Scanner(System.in);
        String espera;
        boolean seleccion = false;
        String opcion;
        String json; //Donde guardaremos lo que nos devuelve el server
        //Guardamos un Array con el codigo de cada equipo en Football-data
        int[] codigos = {86,81,78,559,95,90,94,77,92,89,263,80,745,558,83,278,88,82,79,250};
        int equipoSeleccion = -1;

        System.out.println("");
        System.out.println("          _,...,_\n" +
                "        .'@/~~~\\@'.\n" +
                "       //~~\\___/~~\\\\\n" +
                "      |@\\__/@@@\\__/@|\n" +
                "      |@/  \\@@@/  \\@|\n" +
                "       \\\\__/~~~\\__//\n" +
                "        '.@\\___/@.'  \n" +
                "          `\"\"\"\"\"`");
        System.out.println("\nInformación Futbolística de Primera División");
        System.out.println("Carlos Barroso @carlos_profe_");
        System.out.println();
        System.out.println("Listado de equipos disponibles para consulta: \n" +
                "1.  Real Madrid\t2.  Barcelona\t3.  Atlético \n" +
                "4.  Sevilla\t\t5.  Valencia\t6.  Betis \n" +
                "7.  Villareal\t8.  Bilbao\t\t9.  R. Sociedad \n" +
                "10. Mallorca\t11. Alavés\t\t12. Espanyol \n" +
                "13. Leganés\t\t14. Celta\t\t15. Granada \n" +
                "16. Eibar\t\t17. Levante\t\t18. Getafe\n" +
                "19. Osasuna\t\t20. Valladolid \n");
        System.out.println("Introduzca el número de equipo que desea consultar: ");
        while (!seleccion) {
            try {
                equipoSeleccion = Integer.parseInt(s.nextLine());
                seleccion = true;
            } catch (Exception e) {
                System.out.println("Debe introducir una opción entre 1 y 20");
            }
        }
        if (equipoSeleccion < 21 && equipoSeleccion > 0) {
            try {
                String urlEquipo = "https://api.football-data.org/v2/teams/" + codigos[equipoSeleccion - 1];
                json = leeUrl(urlEquipo); //Paso el HTTP del server a un String
                Equipo equipo = gson.fromJson(json, Equipo.class);
                pintaEquipo(equipo);
                System.out.println("-------------------------------");
                System.out.println("Desea ver en que competiciones participa?? (S/N)");
                opcion = s.nextLine().toUpperCase();
                if (opcion.equals("S")) {
                    System.out.println("Competiciones para este año: ");
                    pintaCompeticiones(equipo);
                }
                System.out.println("-------------------------------");
                System.out.println("Desea ver la plantilla del equipo (S/N)");
                opcion = s.nextLine().toUpperCase();
                if (opcion.equals("S")) {
                    pintaPlantilla(equipo);
                }
                System.out.println("-------------------------------");
                System.out.println("Desea ver los últimos partidos (S/N)");
                opcion = s.nextLine().toUpperCase();
                if (opcion.equals("S")) {
                    String urlPartido = "https://api.football-data.org/v2/teams/" + codigos[equipoSeleccion - 1] + "/matches?status=FINISHED&limit=5";
                    json = leeUrl(urlPartido);
                    Resultados partidos = gson.fromJson(json, Resultados.class);
                    pintaPartidos(partidos);
                }
                System.out.println("-------------------------------");
                System.out.println("Esperemos que haya disfrutado");
                Thread.sleep(5*1000);



            } catch (Exception e) {
                System.out.println("El fichero json no existe");
            }

        }

    }

    private static void pintaEquipo(Equipo equipo) {
        if (equipo != null) {
            System.out.println("Información de un equipo");
            System.out.println("--------------------------");
            System.out.println("Nombre = " + equipo.getName());
            System.out.println("Dirección = " + equipo.getAddress());
            System.out.println("Email = " + equipo.getEmail());
            System.out.println("Colores = " + equipo.getClubColors());
            System.out.println("Web = " + equipo.getWebsite());
        }
    }

    private static void pintaCompeticiones(Equipo equipo){
        List<ActiveCompetition> competiciones;
        competiciones = equipo.getActiveCompetitions();
        for (ActiveCompetition c : competiciones) {
            System.out.println("País = " + c.getArea().getName() + "; Nombre: " + c.getName());
        }
    }

    private static void pintaPartidos (Resultados partidos){
        var s = new Scanner(System.in);
        String espera;
        if (partidos != null) {
            for (Match p : partidos.getMatches()) {
                System.out.println("Información de los últimos partidos");
                System.out.println("--------------------------");
                System.out.println("Competición = " + p.getCompetition().getName());
                System.out.println("Equipo local = " + p.getHomeTeam().getName());
                System.out.println("Equipo visitante = " + p.getAwayTeam().getName());
                System.out.println("Jornada = " + p.getMatchday());
                System.out.println("Resultado = " + p.getScore().getFullTime().getHomeTeam() + " - " + p.getScore().getFullTime().getAwayTeam());
                System.out.println("Árbitro = " + p.getReferees().get(0).getName());
                System.out.println("Pulse C para continuar......");
                espera = s.nextLine();
            }
        }
    }

    private static void pintaPlantilla(Equipo equipo){
        List<Squad> plantilla;
        var s = new Scanner(System.in);
        System.out.println("Jugadores en plantilla: ");
        plantilla = equipo.getSquad();
        String numero, espera;
        int contador = 0;
        for (Squad jugador : plantilla) {
            if (jugador.getShirtNumber() == null) numero = "SN";
            else numero = jugador.getShirtNumber().toString();
            System.out.println("Nombre: " + jugador.getName() + "; Posición: " + jugador.getPosition() + "; Nacionalidad: "
                    + jugador.getNationality() + "; Número: " + numero + "; Rol: " + jugador.getRole());
            contador++;
            if (contador % 5 == 0){
                System.out.println("Pulse C para continuar......");
                espera = s.nextLine();
            }
        }
    }

    private static String leeUrl(String direccionURL) throws Exception {

        BufferedReader lector = null;
        try {
            URL url = new URL(direccionURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Metemos los Headers
            conn.setRequestProperty("Content-Type", "application/json");
            // Aquí hay que meter la key de cada usuario
            conn.setRequestProperty("X-Auth-Token", "aquitukey");
            // Controlo si hay conexión con éxito con el server de la APUI
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Request Failed. HTTP Error Code: " + conn.getResponseCode());
            }
            //Ya solo nos queda pasar la respuesta a un buffer y devolver el String
            lector = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = lector.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (lector != null)
                lector.close();
        }
    }
}
