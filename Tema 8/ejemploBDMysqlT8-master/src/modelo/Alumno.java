package modelo;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Alumno extends Persona{

    private String grupo;
    private double notaMedia;

    public Alumno(String dni, String nombre, String apellidos, GregorianCalendar fechaNacim, String grupo, double notaMedia) {
        super(dni, nombre, apellidos, fechaNacim);
        this.grupo = grupo;
        this.notaMedia = notaMedia;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    @Override
    public String getApellidos() {
        return this.apellidos;
    }

    public void mostrar () {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String Stringfecha= formatoFecha.format(this.fechaNacim.getTime());
        System.out.printf ("Nombre: %s\n", this.nombre);
        System.out.printf ("Apellidos: %s\n", this.apellidos);
        System.out.printf ("Fecha de nacimiento: %s\n", Stringfecha);
        System.out.printf ("Grupo: %s\n", this.grupo);
        System.out.printf ("Nota media: %5.2f\n", this.notaMedia);
    }

}
