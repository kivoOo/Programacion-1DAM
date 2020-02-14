import java.io.*;

public class Persona implements Serializable{

    String nombre;
    String primerApell;
    String segundoApell;
    int edad;
    long dni;
    char sexo;
    boolean estudiante;

    public Persona(String nombre, String primerApell, String segundoApell, int edad, long dni, char sexo,
                   boolean estudiante){
        this.nombre = nombre;
        this.primerApell = primerApell;
        this.segundoApell = segundoApell;
        this.edad = edad;
        this.dni = dni;
        this.sexo = sexo;
        this.estudiante = estudiante;
    }

    public Persona(){
        this.nombre = null;
        this.primerApell = null;
        this.segundoApell = null;
        this.edad = 0;
        this.dni = 0;
        this.sexo = 'n';
        this.estudiante = false;
    }

    //Metodos de nuestra clase
    public int getEdad() {
        return edad;
    }

    public Boolean getEstudiante() {
        return estudiante;
    }

    public char getSexo() {
        return sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public long getDni() {
        return dni;
    }

    public void getNombreCompleto(){
        System.out.println("nombre = " + nombre);
        System.out.println("primerApell = " + primerApell);
        System.out.println("segundoApell = " + segundoApell);
    }

    //Otros métodos

    public void salvar() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.nombre + ".dat"));
        oos.writeObject(this);
        oos.close();
    }

    public Persona leerPersonaDisco(String fichero) throws IOException, ClassNotFoundException {
        Persona p;
        FileInputStream fis = new FileInputStream(fichero);
        ObjectInputStream ois = new ObjectInputStream(fis);
        p = (Persona) ois.readObject();
        ois.close();
        return p;
    }
    public String toString() {
        String texto = "Nombre: " + this.nombre + "\nEdad: " + this.edad + "\nSexo: " +
                this.sexo + "\nEstudiante: " + this.estudiante;
        return texto;
    }
}
