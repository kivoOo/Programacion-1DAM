import java.io.*;

public class Cuenta {
    private long numero;
    private String titular;
    private float saldo;
    private float interesAnual;
    private static String banco = "Santander";
    private static int contadorCuentas;
    // Atributo estatico para contar las
    // cuentas creadas
    //Constructor
    public Cuenta(long numero, String titular, float interesAnual){
        this.numero = numero;
        this.titular= titular;
        this.saldo = 0;
        this.interesAnual = interesAnual;
        contadorCuentas++;
    }
    //Constructor de la clase cuenta que lee de fichero
    public Cuenta(long aNumero) throws FileNotFoundException, IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(aNumero + ".cnt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        numero = aNumero;
        titular = (String) ois.readObject();
        saldo = ois.readFloat();
        interesAnual = ois.readFloat();
        contadorCuentas++;
        ois.close();
    }
    //Constructor copia
    public Cuenta (Cuenta cuenta){
        this.interesAnual = cuenta.getInteresAnual();
        this.saldo = cuenta.getSaldo();
        this.titular = cuenta.getTitular();
        this.numero = cuenta.getNumero();
        contadorCuentas++;
    }

    //Setter de la clase
    public void setInteresAnual(float interesAnual) {
        this.interesAnual = interesAnual;
    }
    public void setNumero(long numero) {
        this.numero = numero;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
    public void setSaldo(float saldo){
        this.saldo = saldo;
    }
    //getter de la clase
    public float getInteresAnual() {
        return interesAnual;
    }
    public float getSaldo() {
        return saldo;
    }
    public static int getContadorCuentas() {
        return contadorCuentas;
    }
    public long getNumero() {
        return numero;
    }
    public static String getBanco() {
        return banco;
    }
    public String getTitular() {
        return titular;
    }

    //Otros metodos
    public void cambiaTitualr(String titular){
        this.titular = titular;
    }
    public void ingreso (float cantidad ){
        saldo +=cantidad;
    }
    public void reintegro (float cantidad){
        saldo -=cantidad;
    }
    public void reintegro (float cantidad, float comision){
        float totalReintegro;
        totalReintegro = cantidad + comision;
        saldo -=totalReintegro;
    }
    public void ingresoInteresMes(){
        saldo += interesAnual + saldo / 1200;
    }
    public boolean enRojos(){return (saldo<0);}
    public static float eurosAPesetas (float euros){return euros*166.386f;}
    public void salvarCuenta() throws IOException {
        FileOutputStream fos = new FileOutputStream(numero + ".cnt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(titular);
        oos.writeFloat(saldo);
        oos.writeFloat(interesAnual);
        oos.close();
    }
}
