package modelo;

import java.util.GregorianCalendar;

public abstract class Persona {

    protected String nombre;
    protected String apellidos;
    protected GregorianCalendar fechaNacim;


    public Persona(String nombre, String apellidos, GregorianCalendar fechaNacim) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacim = fechaNacim;
    }

    public String getNombre() {
        return nombre;
    }

    protected abstract void mostrar ();

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
