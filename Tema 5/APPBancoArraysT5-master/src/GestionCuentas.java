import java.util.Arrays;

public class GestionCuentas {
    Cuenta[] cuentas;//Creo un array de 10 cuentas, el banco
    // no podrá tener más cuentas
    int numCuentas;

    public GestionCuentas(){
        this.cuentas = new Cuenta[10];
        this.numCuentas = 0;
    }

    public int getNumCuentas() {
        return numCuentas;
    }
    public Cuenta[] getCuentas() {
        return cuentas;
    }

    public Cuenta getCuenta(int pos){
        return cuentas[pos];
    }

    public int buscarCuenta(long numero){
        for (Cuenta c: cuentas) {
            if (c != null) {
                if (c.getNumero() == numero) {
                    return Arrays.asList(cuentas).indexOf(c);
                }
            }
        }
        return -1;
    }
    public Boolean añadirCuenta(Cuenta cuenta) {
        Boolean resultado = false;
        if (numCuentas < 10) { // Si hay 9 o menos cuentas
            for (Cuenta c : cuentas) { //Busco un hueco en el array que estr a null
                if (c == null) {
                    this.cuentas[numCuentas] = new Cuenta(cuenta);//Utilizo el constructor copia
                    numCuentas++; //Actualizo el numero de cuentas
                    resultado = true; //Pongo éxito
                    break;
                }
            }
        }
        return resultado;
    }

    public Boolean borrarCuenta(Long numero){
        int pos = this.buscarCuenta(numero);
        if (pos == -1) return false;
        else{
            cuentas[pos] = null; //La posición en el array es numcuentas-1
            numCuentas--;
            return true;
        }
    }
    public Cliente buscarCliente(String dni){
        for (Cuenta c: cuentas) {
            if (c != null){
                if (c.getTitular().getDni().equalsIgnoreCase(dni)) return c.getTitular();
            }
        }
        return null;
    }
    public Cuenta[] buscarCuentasCliente(String dni){
        Cuenta[] cuentasCliente = new Cuenta[10]; //Como mucho un cliente puede tener 10 cuentas
        int contador = 0;
        for (Cuenta c:this.cuentas) {
            if (c != null){
                if (c.getTitular().getDni().equalsIgnoreCase(dni)){
                    cuentasCliente[contador] = new Cuenta(c);
                    contador++;
                }
            }
        }
        return cuentasCliente;
    }

    public Cuenta[] buscarCuentasenRojos(){
        Cuenta[] cuentasenRojos = new Cuenta[10];
        int contador = 0;
        for (Cuenta c:this.cuentas) {
            if (c != null) {
                if (c.enRojos()) {
                    cuentasenRojos[contador] = new Cuenta(c);
                    contador++;
                }
            }
        }
        return cuentasenRojos;
    }

    public void ingresarInteres(){
        for (Cuenta c:this.cuentas) {
            if (c != null) {
                c.ingresoInteresMes();
            }
        }
    }
}
