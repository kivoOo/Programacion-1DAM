
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {


        MarcoServidor mimarco = new MarcoServidor();

    }
}

class MarcoServidor implements Runnable {

    public MarcoServidor() {

        Thread mihilo = new Thread(this);

        mihilo.start();

    }

    @Override
    public void run() {
        System.out.println("Estoy a la escucha");
        try {
            ServerSocket miServer = new ServerSocket(9999);
            String nick, ip, mensaje;
            Cliente.PaqueteEnvio paqueteEntrada = new Cliente.PaqueteEnvio();
            while (true) {
                Socket miSocket = miServer.accept();
                ObjectInputStream FlujoEntrada = new ObjectInputStream(miSocket.getInputStream());
                paqueteEntrada = (Cliente.PaqueteEnvio) FlujoEntrada.readObject();
                System.out.println("Emisor = " + paqueteEntrada.getEmisor());
                System.out.println("IP Dest = " + paqueteEntrada.getIp());
                System.out.println("Mensaje = " + paqueteEntrada.getMensaje());
                Socket envioDestinatario = new Socket(paqueteEntrada.getIp(),9090);
				ObjectOutputStream FlujoReenvio = new ObjectOutputStream((envioDestinatario.getOutputStream()));
				FlujoReenvio.writeObject(paqueteEntrada);
				FlujoEntrada.close();
				envioDestinatario.close();
                miSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
