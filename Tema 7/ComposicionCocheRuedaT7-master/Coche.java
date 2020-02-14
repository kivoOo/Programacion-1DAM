import java.util.ArrayList;

public class Coche {

    private String marca;
    private String modelo;
    private int caballos;
    private Ruedas gomas;

    public Coche(String marca, String modelo, int caballos, Ruedas gomas) {
        this.marca = marca;
        this.modelo = modelo;
        this.caballos = caballos;
        this.gomas = gomas;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCaballos() {
        return caballos;
    }

    public void setCaballos(int caballos) {
        this.caballos = caballos;
    }

    public Ruedas getGomas() {
        return gomas;//OJO aqui, no debemos devolver la referencia a nuestro objeto interno.
        //Deberiamos devolver un objeto new
    }

    public void setGomas(Ruedas gomas) {
        this.gomas = gomas;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", caballos=" + caballos +
                ", gomas=" + gomas +
                '}';
    }
}
