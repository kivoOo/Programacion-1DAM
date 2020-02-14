package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Profesor extends Persona {

    private String materia;
    private double salario;
    private Date nacimiento;

    public Profesor(String dni, String nombre, String apellidos, GregorianCalendar fechaNacim, String materia, double salario) {
        super(dni, nombre, apellidos, fechaNacim);
        this.materia = materia;
        this.salario = salario;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void mostrar () {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String Stringfecha= formatoFecha.format(this.fechaNacim.getTime());
        System.out.printf ("Nombre: %s\n", this.nombre);
        System.out.printf ("Apellidos: %s\n", this.apellidos);
        System.out.printf ("Fecha de nacimiento: %s\n", Stringfecha);
        System.out.printf ("Especialidad: %s\n", this.materia);
        System.out.printf ("Salario: %7.2f euros\n", this.salario);
    }

}

