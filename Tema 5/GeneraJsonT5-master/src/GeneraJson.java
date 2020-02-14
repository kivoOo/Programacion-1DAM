import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class GeneraJson {

    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("carlos","barroso moriana","12345678Z","637766543",
                "carlos@carlos.com", new Date());
        Cliente cliente2 = new Cliente("pepe","lopez torres","12345678Z","756766543",
                "pepe@carlos.com", new Date());
        Cliente cliente3 = new Cliente("juan","cano marchal","12345678Z","693839372",
                "juan@carlos.com", new Date());

        Cuenta cuenta1= new Cuenta (1,cliente1,2.0f);
        Cuenta cuenta2= new Cuenta (2,cliente2,1.0f);
        Cuenta cuenta3= new Cuenta (3,cliente3,1.5f);

        ArrayList<Cuenta> listadoCuentas = new ArrayList<>();
        listadoCuentas.add(cuenta1);
        listadoCuentas.add(cuenta2);
        listadoCuentas.add(cuenta3);

        Gson gson = new Gson();
        //System.out.println(gson.toJson(listadoCuentas));
        escribeFicheroJson(gson.toJson(listadoCuentas));
        System.out.println("Fichero JSON escrito correctamente");
    }

    private static void escribeFicheroJson(String gson){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("cuentas.json");
            pw = new PrintWriter(fichero);
            pw.println(gson);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
