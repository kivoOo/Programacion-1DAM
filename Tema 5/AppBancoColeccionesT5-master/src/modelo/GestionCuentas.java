package modelo;

import modelo.Cliente;
import modelo.Cuenta;

import java.util.ArrayList;

public class GestionCuentas {
    private ArrayList<Cuenta> cuentas;

    //Constructor
    public GestionCuentas(){
        this.cuentas = new ArrayList<Cuenta>();
    }
//Getters
    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public Cuenta getCuenta(int pos){
        return cuentas.get(pos);
    }
//Otros métodos

    public int contarCuentas(){
        return cuentas.size();
    }

    public int buscarCuenta(Long numero){
        for (Cuenta c: cuentas) {
            if (c.getNumero() == numero){
                return cuentas.indexOf(c);
            }
        }
        return -1;
    }

    public int buscarCuentaMovil(String movil){
        for (Cuenta c: cuentas) {
            if (c.getTitular().getNumMovil().equalsIgnoreCase(movil)){
                return cuentas.indexOf(c);
            }
        }
        return -1;
    }

    public long añadirCuenta(Cliente titular, float interesAnual) {
        Cuenta c;
        long numero = 0;
        boolean numCuenta = false;
        while (!numCuenta){ //Compruebo si el numero ya esta asignado
            numero = (long) (Math.random() * 100000 + 1);
            if (this.buscarCuenta(numero) == -1) numCuenta = true;
        }
        c = new Cuenta(numero, titular, interesAnual);
        cuentas.add(c);
        return numero;
    }

    public Boolean borrarCuenta(Long numero){
        int pos = this.buscarCuenta(numero);
        if (pos == -1) return false;
        else {
            cuentas.remove(pos);
            return true;
        }
    }
    public Cliente buscarCliente(String dni){
        for (Cuenta c: cuentas) {
            if (c.getTitular().getDni().equalsIgnoreCase(dni)) return c.getTitular();
        }
        return null;
    }
    public ArrayList<Cuenta> buscarCuentasCliente(String dni){
        ArrayList<Cuenta> cuentasCliente = new ArrayList<Cuenta>(); //Como mucho un cliente puede tener 10 cuentas
        Cuenta cuentaAux;
        for (Cuenta c:this.cuentas) {
            if (c.getTitular().getDni().equalsIgnoreCase(dni)){
                cuentaAux = new Cuenta(c);
                cuentasCliente.add(cuentaAux);
            }
        }
        return cuentasCliente;
    }

    public ArrayList<Cuenta> buscarCuentasenRojos(){
        ArrayList<Cuenta> cuentasEnRojos = new ArrayList<Cuenta>(); //Como mucho un cliente puede tener 10 cuentas
        Cuenta cuentaAux;
        for (Cuenta c:this.cuentas) {
            if (c.enRojos()){
                cuentaAux = new Cuenta(c);
                cuentasEnRojos.add(cuentaAux);
            }
        }
        return cuentasEnRojos;
    }

    public void ingresarInteres(){
        for (Cuenta c:this.cuentas) {
                c.ingresoInteresMes();
        }
    }

    public ArrayList<Cliente> buscarClientesTexto(String texto){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>(); //Como mucho un cliente puede tener 10 cuentas
        Cliente clienteAux;
        for (Cuenta c: cuentas) {
            if ((c.getTitular().getNombre().toUpperCase().indexOf(texto.toUpperCase()) != -1)
            || (c.getTitular().getApellidos().toUpperCase().indexOf(texto.toUpperCase()) != -1)){ //Compruebo si el texto
                //esta en el nombre o en los apellidos
                clienteAux = new Cliente(c.getTitular());
                clientes.add(clienteAux);
            }
        }
        return clientes;
    }

}
