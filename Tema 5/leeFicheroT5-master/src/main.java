import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {

    public static void main(String[] args) {
    String contenido ="";
        try{
            contenido = leeFichero("palabras.csv");
        }catch (IOException e){
            System.out.println("No he podido encontrar el fichero");
        }
        System.out.println("contenido = " + contenido);
    }

    public static String leeFichero (String archivo) throws IOException {
        String cadena;
        String contenido = "";
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null){
            contenido = contenido + cadena + "\n";
        }
        b.close();
        return contenido;
    }
}
