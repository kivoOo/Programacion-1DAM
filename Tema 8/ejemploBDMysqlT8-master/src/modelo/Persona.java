package modelo;

import java.util.GregorianCalendar;

public abstract class Persona {

    protected String dni;
    protected String nombre;
    protected String apellidos;
    protected GregorianCalendar fechaNacim;


    public Persona(String dni, String nombre, String apellidos, GregorianCalendar fechaNacim) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacim = fechaNacim;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void mostrar ();

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public GregorianCalendar getFechaNacim() {
        return fechaNacim;
    }

    public void setFechaNacim(GregorianCalendar fechaNacim) {
        this.fechaNacim = fechaNacim;
    }
}
