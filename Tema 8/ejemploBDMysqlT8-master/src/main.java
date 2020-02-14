import DAO.DAOManager;
import DAO.DaoAlumnoSQL;
import DAO.DaoProfesor;
import DAO.DaoProfesorSQL;
import modelo.Alumno;
import modelo.Profesor;
import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        var s = new Scanner(System.in);


        DAOManager dao = DAOManager.getSinglentonInstance();
        DAOManager dao2 = DAOManager.getSinglentonInstance();
        if (dao2 == null) System.out.println("El singlenton funciona");
        try{
            dao.open();
            System.out.println("Conexión establecida");
        }catch (Exception e){
            System.out.println("Error de conexión en la BBDD");
        }


        DaoAlumnoSQL daoAlumnoSQL = new DaoAlumnoSQL();
        DaoProfesorSQL daoProfesorSQL = new DaoProfesorSQL();
        System.out.println("--------------------------------");
        System.out.println("Trabajando con alumnos......");
        GregorianCalendar fechaN = new GregorianCalendar();
        Alumno carlos = new Alumno("77322948F", "Carlos", "Barroso Moriana", fechaN, "1DAM", 6.5f);
        if (daoAlumnoSQL.insert(carlos, dao)) System.out.println("Grabación correcta");
        else System.out.println("Ya existía el alumno");
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("Trabajando con profesorado......");
        Profesor pepe = new Profesor("77322948F", "Pepe", "Lopez Caro", fechaN, "Programación", 1700);
        if (daoProfesorSQL.insert(pepe, dao)) System.out.println("Grabación correcta");
        else System.out.println("Ya existía el profe");
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("Buscando el DNI 77322947Y en alumnos");
        Alumno a2 = daoAlumnoSQL.read("77322947Y", dao);
        if (a2 == null) System.out.println("Alumno no encontrado");
        else System.out.println("Alumno encontrado");
        System.out.println("Pulse una tecla.....");
        s.next();
        System.out.println("Buscando el DNI 77322948F");
        a2 = daoAlumnoSQL.read("77322948F", dao);
        if (a2 == null) System.out.println("Alumno no encontrado");
        else {
            System.out.println("Alumno encontrado");
            a2.mostrar();
        }

        System.out.println("-----------------------------------");
        System.out.println("Buscando el DNI 77322947Y en profes");
        Profesor p2 = daoProfesorSQL.read("77322947Y", dao);
        if (p2 == null) System.out.println("Profesor no encontrado");
        else System.out.println("Profesor encontrado");
        System.out.println("Pulse una tecla.....");
        s.next();
        System.out.println("Buscando el DNI 77322948F");
        p2 = daoProfesorSQL.read("77322948F", dao);
        if (p2 == null) System.out.println("Profesor no encontrado");
        else {
            System.out.println("Profesor encontrado");
            p2.mostrar();
        }

        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("Vamos a modificar un alumno");
        s.next();
        //Ahora modificamos el alumno
        Alumno a3 = new Alumno("77322948F", "Alba", "Barroso Ramiro", fechaN, "2ASIR", 10);
        if (daoAlumnoSQL.read("77322948F", dao) == null) System.out.println("El usuario no se encuentre en nuestra BBDD");
        else {
            if (daoAlumnoSQL.update(a3, dao)) {
                System.out.println("Modificación efectuada!!!");
                Alumno a4 = daoAlumnoSQL.read("77322948F", dao);
                a4.mostrar();
            }else System.out.printf("Algo ha fallado en la modificación");
        }


    }

}
