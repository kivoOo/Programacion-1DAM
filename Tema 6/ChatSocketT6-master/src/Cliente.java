
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;


public class Cliente {

    static ArrayList<PaqueteEnvio> mensajes = new ArrayList<>();

    public static void main(String[] args) {

        var s = new Scanner(System.in);
        String mensaje;
        int op = 0;
        Socket miSocket = null;
        Properties propiedades = new Properties();
        try {
            propiedades.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.getMessage();
        }
        while (op < 3) {
            System.out.println("Bienvenido a la aplicación de Chat del IES Fernando III de Martos");
			System.out.println("Servidor: " + propiedades.getProperty("server") + " ; Puerto: " +
                    propiedades.getProperty("port"));
            System.out.println("Tu nick: " + propiedades.getProperty("nick") + " ; IP Destino: " +
                    propiedades.getProperty("ip_dest"));
			System.out.println("1. Entrar al chat");
			System.out.println("2. Cambiar los parámetros de conexión");
			System.out.println("3. Desconectar");
			System.out.print("Elija una opción: ");
			op = Integer.parseInt(s.nextLine());
            switch (op) {
                case 1: //Entrar al chat
                    mensaje = "";
                    MarcoCliente miMarco = new MarcoCliente();
                    ObjectOutputStream flujoSalida = null;
                    System.out.println("Bienvenido al chat. Está chateando con: " + propiedades.getProperty("ip_dest"));
                    System.out.println("Tu nick es: " + propiedades.getProperty("nick"));
                    while (!mensaje.equals("-1")){
                        pintarMensajes(mensajes);
                        try {
                            System.out.print("\nIntroduzca el mensaje a enviar: (R --> Recargar mensajes) (-1 --> Salir)");
                            mensaje = s.nextLine();
                            if (!mensaje.equals("-1") && !mensaje.equals("R")){
                                miSocket = new Socket(propiedades.getProperty("server"), Integer.parseInt(propiedades.getProperty("port")));
                                flujoSalida = new ObjectOutputStream(miSocket.getOutputStream());
                                PaqueteEnvio envio= new PaqueteEnvio();
                                envio.setEmisor(propiedades.getProperty("nick"));
                                envio.setIp(propiedades.getProperty("ip_dest"));
                                envio.setMensaje(mensaje);
                                System.out.println(miSocket.isClosed());
                                flujoSalida.writeObject(envio);
                                flujoSalida.close();
                                mensajes.add(envio);
                            }
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }

                    break;
                case 2: //Cambiar propiedades
                    System.out.print("Introduzca el nuevo servidor: ");
                    String server = s.nextLine();
                    propiedades.setProperty("server",server);
                    System.out.print("\nIntroduzca el nuevo puerto: ");
                    String puerto = s.nextLine();
                    propiedades.setProperty("port",puerto);
                    System.out.print("\nIntroduzca el nuevo nick: ");
                    String nick = s.nextLine();
                    propiedades.setProperty("nick",nick);
                    System.out.print("\nIntroduzca la ip de destino: ");
                    String ip = s.nextLine();
                    propiedades.setProperty("ip_dest",ip);
                    try {
                        propiedades.store(new BufferedWriter(new FileWriter("config.properties")),"");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Debe reiniciar el programa para que los cambios tengan efecto.");

                    break;
                case 3: //Salir
                    System.out.println("Finalizando el programa............");
                    break;
            }
        }
    }

    public static void pintarMensajes(ArrayList <PaqueteEnvio> mensajes){
        System.out.println("Mensajes de chat: ");
        System.out.println("Emisor\t\t\t\tContenido");
        System.out.println("----------------------------------");
        for (PaqueteEnvio m:mensajes) {
            System.out.println(m.getEmisor() + "\t\t\t" + m.getMensaje());
        }
    }



    static class PaqueteEnvio implements Serializable{
        private String mensaje;
        private String emisor;
        private String ip;

        public void setEmisor(String emisor) {
            this.emisor = emisor;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getEmisor() {
            return emisor;
        }

        public String getIp() {
            return ip;
        }

        public String getMensaje() {
            return mensaje;
        }
    }

}

class MarcoCliente implements Runnable {

    public MarcoCliente() {

        Thread mihilo = new Thread(this);

        mihilo.start();

    }

    @Override
    public void run() {
        try {
            ServerSocket serverCliente = new ServerSocket(9090);
            Socket cliente;
            Cliente.PaqueteEnvio paqueteRecibido = new Cliente.PaqueteEnvio();
            while (true) {
                cliente = serverCliente.accept();
                ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
                paqueteRecibido = (Cliente.PaqueteEnvio) flujoEntrada.readObject();
                Cliente.mensajes.add(paqueteRecibido);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}