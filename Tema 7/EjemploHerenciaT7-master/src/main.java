import modelo.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class main {

    public static void main(String[] args) {

        GregorianCalendar c1 = new GregorianCalendar();
        c1.set(2001, Calendar.APRIL, 23); //Fecha nacimiento del profe
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Alumno pepe = new Alumno("pepe","lopez caro",c1,"1DAM",8.4);
        c1.set(1983,Calendar.SEPTEMBER,10); //Fecha nacimiento del alumno
        Profesor carlos = new Profesor("carlos","barroso moriana",c1,"Programación",100);
        System.out.println("Datos del profesor");
        carlos.mostrar();
        System.out.println();
        System.out.println("Datos del alumno");
        pepe.mostrar();
    }
}
