
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        var s=new Scanner(System.in);
        System.out.println("Bienvenido al banco");
        System.out.println("Introduce el nombre del titular: ");
        var nombre = s.nextLine();
        System.out.println("Introduce los apellidos: ");
        var apellidos = s.nextLine();
        System.out.println("Introduce el dni: ");
        var dni = s.nextLine();
        System.out.println("Introduce el movil: ");
        var movil = s.nextLine();
        System.out.println("Introduce email: ");
        var email = s.nextLine();
        System.out.println("Introduce fecha de nacimiento: dd/MM/yyyy ");
        var fechaNac = s.nextLine();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date testDate = null;
        String date = fechaNac;
        try{
            testDate = df.parse(date);
        } catch (Exception e){
            System.out.println("Fecha de nacimiento en formato no válido");
            return;
        }
        System.out.println("Vamos a crear el cliente .......");
        try{
            Cliente cli1 = new Cliente(nombre, apellidos, dni, movil, email, testDate);
            System.out.println("Cliente creado correctamente");
            System.out.println("Ahora le abrimos una cuenta al cliente.....");
            Cuenta cuenta1 = new Cuenta(1,cli1,1.5f);
            cuenta1.setSaldo(1500.00f);
            Date fecha = new Date();
            Cliente cli2 = new Cliente("Pepe","Lopez","25934650B",
                    "12","23", fecha);
            Cuenta cuenta2 = new Cuenta(2,cli2,1.0f);
            System.out.println("Vamos a hacer dos reintegros sobre la cuenta 2");
            if (!cuenta2.enRojos()){
                cuenta2.reintegro(350.5f);
            }
            if (!cuenta2.enRojos()){
                cuenta2.reintegro(350.5f);
                System.out.println("cuenta2.getSaldo() = " + cuenta2.getSaldo());
            }else{
                System.out.println("No se ha podido hacer el reintegro, cuenta en numeros rojos");
            }
            System.out.println("Titular cuenta2 = " + cuenta2.getTitular().getNombre() +" "+cuenta2.getTitular().getApellidos());
            System.out.println("cuenta2.getSaldo() = " + cuenta2.getSaldo());
        }catch (IOException e){
            System.out.println("Hubo un fallo al crear el Cliente, el DNI era correcto??");
            return;
        }
        System.out.println("Vamos a finalizar el programa...");

    }
}
