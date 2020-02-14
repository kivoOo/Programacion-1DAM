import java.io.IOException;
import java.util.Date;

public class Cliente {

    private String nombre;
    private String apellidos;
    private String dni;
    private String numMovil;
    private String email;
    private Date fechNac;

    //Constructor
    public Cliente(String nombre, String apellidos, String dni, String numMovil,
                   String email, Date fechNac){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this. numMovil = numMovil;
        this.email = email;
        this.fechNac = fechNac;
        this.dni = dni;
    }

    //Setter
    public void setDni(String dni) {
        this.dni = dni;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setFechNac(Date fechNac) {
        this.fechNac = fechNac;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void setNumMovil(String numMovil){
        this.numMovil = numMovil;
    }

    //Getter
    public String getEmail() {
        return email;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getNombre() {
        return nombre;
    }
    public Date getFechNac() {
        return fechNac;
    }
    public String getDni() {
        return dni;
    }
    public String getNumMovil() {
        return numMovil;
    }


}

