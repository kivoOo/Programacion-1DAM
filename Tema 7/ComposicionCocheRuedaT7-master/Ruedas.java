import org.w3c.dom.ls.LSOutput;

public class Ruedas {

    private String marca;
    private String modelo;
    private String ancho;
    private char estacion;

    public Ruedas(String marca, String modelo, String ancho, char estacion) {
        this.marca = marca;
        this.modelo = modelo;
        this.ancho = ancho;
        this.estacion = estacion;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAncho() {
        return ancho;
    }

    public char getEstacion() {
        return estacion;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public void setEstacion(char estacion) {
        this.estacion = estacion;
    }

    @Override
    public String toString() {
        return "ruedas{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ancho='" + ancho + '\'' +
                ", estacion=" + estacion +
                '}';
    }


}
