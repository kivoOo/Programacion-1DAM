import modelo.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class main {

    static final float COMISION = 0.25f;
    static Scanner S = new Scanner(System.in);
    public static void main(String[] args) {

        int op = 0;
        String teclaContinuar;
        boolean numCuentaValido = false;
        var gestion = insertaDatos();
        Date fecha;
        float interes;
        int pos = -1; //variable para saber posiciones de cuentas en array

        while (op < 9  ) {
            op = 0;
            System.out.println("\nBienvenido al banco Docente");
            System.out.println("Actualmente tenemos " + gestion.contarCuentas() + " cuentas");
            System.out.println("Elija una opción y pulse Enter \n");
            System.out.println("-----------------------------------------");
            System.out.println("1. Crear una Cuenta Bancaria");
            System.out.println("2. Borrar una Cuenta Bancaria");
            System.out.println("3. Consultar datos de un Cliente o Cuenta");
            System.out.println("4. Cambiar los datos de una Cuenta");
            System.out.println("5. Realizar una operación entre Cuentas");
            System.out.println("6. Buscar Cuentas en números rojos");
            System.out.println("7. Ingresar el interés mensual en las Cuentas");
            System.out.println("8. Reaizar un bizum");
            System.out.println("9. Salir");
            System.out.print("Introduzca la opción deseada: ");
            while (op == 0) {
                try {
                    op = Integer.parseInt(S.nextLine());
                    if (op>9) {
                        op=0;
                        System.out.print("Introduzca una opción válida por favor: ");
                    }
                } catch (Exception e) {
                    System.out.print("Introduzca una opción válida por favor: ");
                }
            }
            switch (op){
                case 1: //Creacion de cuentas. IMPORTANTE: COMPROBAR SI EL CLIENTE YA EXISTE
                    System.out.print("Por favor introduzca el DNI del cliente: ");
                    String dni = S.nextLine().toUpperCase();
                    interes = -1;
                    if (!comprobarDNI(dni)){
                        System.out.println("Ha introducido un DNI incorrecto");
                    }else{
                        Cliente c = gestion.buscarCliente(dni);
                        if (c == null){ //Si el cliente no existe
                            System.out.println("Esta introduciendo un DNI nuevo en el sistema");
                            c = nuevoCliente(dni);
                            while (interes < 0) {
                                try {
                                    System.out.print("Introduzca el interés a aplicar: ");
                                    interes = Float.parseFloat(S.nextLine());
                                } catch (Exception e) {
                                    System.out.print("Introduzca un interés válido: ");
                                }
                            }
                            long num = gestion.añadirCuenta(c,interes);
                            System.out.println("La cuenta ha sido creada, " +
                                    "el número de cuenta es: " + num);

                        }else{ //Si el cliente ya existe no hay que crearlo
                            System.out.println("Va a abrir una Cuenta al siguiente cliente: ");
                            pintarCliente(c);
                            System.out.println();
                            while (interes < 0) {
                                try {
                                    System.out.print("Introduzca el interés a aplicar: ");
                                    interes = Float.parseFloat(S.nextLine());
                                } catch (Exception e) {
                                    System.out.print("Introduzca un interés válido: ");
                                }
                                long num = gestion.añadirCuenta(c,interes);
                                System.out.println("La cuenta ha sido creada, " +
                                        "el número de cuenta es: " + num);
                            }
                        }
                    }
                    System.out.println("Pulse enter para continuar........");
                    teclaContinuar = S.nextLine();
                    break;
                case 2: //Borrado de cuentas
                    numCuentaValido = false;
                    long numCuenta = -1;
                    pos = -1;
                    boolean resultado = false;
                    while (!numCuentaValido){
                        System.out.print("Por favor introduzca el número de cuenta a eliminar: ");
                        try{
                            numCuenta = Long.parseLong(S.nextLine());
                            numCuentaValido = true;
                        }catch (Exception e) {
                            System.out.println("\nEl número de cuenta no ha sido introducido correctamente");
                        }
                    }
                    pos = gestion.buscarCuenta(numCuenta);
                    if (pos == -1) System.out.println("\nLa cuenta no se encuentra en nuestros registros");
                    else {
                        System.out.println("Va a elimiar la siguiente cuenta: ");
                        pintarCuenta(gestion.getCuenta(pos));
                        System.out.println("Confirme la eliminación pulsando S");
                        String confirmacion = S.nextLine();
                        if (confirmacion.equalsIgnoreCase("S")) resultado = gestion.borrarCuenta(numCuenta);
                    }
                    if (resultado) System.out.printf("La operación se realizó con éxito, cuenta %d borrada\n",numCuenta);
                    else System.out.println("La operación no se pudo realizar");
                    System.out.println("Pulse enter para continuar........");
                    teclaContinuar = S.nextLine();
                    break;
                case 3: //Consulta de datos cliente o cuenta
                    int consulta =0;
                    System.out.println("Desea consultar los datos de un Cliente o de una Cuenta: ");
                    System.out.println("1. Cliente");
                    System.out.println("2. Cuenta");
                    try{
                        consulta = Integer.parseInt(S.nextLine());
                    }catch (Exception e){
                        System.out.println("La opción introducida no es válida");
                    }
                    if (consulta < 3 && consulta >0){
                        if (consulta == 1) consultarDatosCliente(gestion);
                        if (consulta == 2) consultarDatosCuenta(gestion);
                    }
                    System.out.println("Pulse enter para continuar........");
                    teclaContinuar = S.nextLine();
                    break;
                case 4: // Cambia los datos de una cuenta. Solo permite cambio de interés
                    numCuentaValido = false;
                    numCuenta = 0;
                    pos = -1;
                    System.out.print("Por favor introduzca el número de cuenta a modificar: ");
                    try{
                        numCuenta = Long.parseLong(S.nextLine());
                        numCuentaValido = true;
                    }catch (Exception e) {
                        System.out.println("\nEl número de cuenta no ha sido introducido correctamente");
                    }
                    if (numCuentaValido){
                        pos = gestion.buscarCuenta(numCuenta);
                        if (pos == -1) System.out.println("La cuenta no existe en nuestros registros");
                        else{
                            System.out.println("Va a modificar la siguiente cuenta: ");
                            pintarCuenta(gestion.getCuenta(pos));
                            System.out.print("Introduzca el nuevo tipo de interés: ");
                            interes=0f;
                            try {
                                interes = Float.parseFloat(S.nextLine());
                                gestion.getCuenta(pos).setInteresAnual(interes);
                            }catch (Exception e){
                                System.out.println("Fallo al introducir el interés");
                            }
                            System.out.println("La cuenta fue modificada correctamente.");
                        }
                    }
                    System.out.println("Pulse enter para continuar........");
                    teclaContinuar = S.nextLine();
                    break;
                case 5: //Operaciones entre cuentas
                    int operacion =0;
                    numCuentaValido = false;
                    numCuenta = 0;
                    float retirada, ingreso, traspaso;
                    String comision;
                    System.out.println("Elija la operación que desea realizar: ");
                    System.out.println("1. Sacar dinero");
                    System.out.println("2. Ingresar dinero");
                    System.out.println("3. Traspaso entre cuentas");
                    try{
                        operacion = Integer.parseInt(S.nextLine());
                    }catch (Exception e){
                        System.out.println("La opción introducida no es válida");
                    }
                    if (operacion < 4 && operacion >0){
                        if (operacion == 1) { //Sacar dinero
                            System.out.print("Introduzca la cuenta para la retirada de efectivo: ");
                            try{
                                numCuenta = Long.parseLong(S.nextLine());
                                numCuentaValido = true;
                            }catch (Exception e) {
                                System.out.println("\nEl número de cuenta no ha sido introducido correctamente");
                            }
                            if (numCuentaValido){
                                if (gestion.buscarCuenta(numCuenta) == -1) System.out.println("\nLa cuenta no se " +
                                        "encuentra en nuestros registros");
                                else{
                                    if (gestion.getCuenta(gestion.buscarCuenta(numCuenta)).enRojos())
                                        System.out.println("\n No puede retirar efectivo de una cuenta en números rojos");
                                    else{
                                        System.out.print("\nIntroduzca la cantidad a retirar: ");
                                        try{
                                            retirada = Float.parseFloat(S.nextLine());
                                            System.out.println("¿Se le aplica la comisión de 0.5? S/N");
                                            comision = S.nextLine().toUpperCase();
                                            if (comision.equalsIgnoreCase("S"))
                                                gestion.getCuenta(gestion.buscarCuenta(numCuenta)).reintegro(retirada,COMISION);
                                            else
                                                gestion.getCuenta(gestion.buscarCuenta(numCuenta)).reintegro(retirada);
                                            System.out.println("La retirada ha sido ejecutada con éxito.");
                                            System.out.println("El nuevo saldo es: " +gestion.getCuenta(gestion.buscarCuenta(numCuenta)).getSaldo());
                                        }catch (Exception e){
                                            System.out.println("La cantidad no es válida");
                                        }
                                    }
                                }
                            }
                        }else {
                            if (operacion == 2) {//Ingresar dinero
                                System.out.print("Introduzca la cuenta para ingresar efectivo: ");
                                try {
                                    numCuenta = Long.parseLong(S.nextLine());
                                    numCuentaValido = true;
                                } catch (Exception e) {
                                    System.out.println("\nEl número de cuenta no ha sido introducido correctamente");
                                }
                                if (numCuentaValido) {
                                    if (gestion.buscarCuenta(numCuenta) == -1) System.out.println("\nLa cuenta no se " +
                                            "encuentra en nuestros registros");
                                    else {
                                        System.out.print("\nIntroduzca la cantidad a ingresar: ");
                                        try {
                                            ingreso = Float.parseFloat(S.nextLine());
                                            gestion.getCuenta(gestion.buscarCuenta(numCuenta)).ingreso(ingreso);
                                            System.out.println("La cantidad ha sido ingresada en cuenta");
                                            System.out.println("El nuevo saldo es: " +
                                                    gestion.getCuenta(gestion.buscarCuenta(numCuenta)).getSaldo());
                                        } catch (Exception e) {
                                            System.out.println("La cantidad no es válida");
                                        }
                                    }
                                }
                            } else {// Traspaso entre cuentas
                                System.out.print("Introduzca la cuenta origen: ");
                                String cuentaOrigen = S.nextLine();
                                System.out.print("Introduzca la cuenta destino: ");
                                String cuentaDestino = S.nextLine();
                                long numCuentaOrigen = 0;
                                long numCuentaDestino = 0;
                                try {
                                    numCuentaOrigen = Long.parseLong(cuentaOrigen);
                                    numCuentaDestino = Long.parseLong(cuentaDestino);
                                    numCuentaValido = true;
                                } catch (Exception e) {
                                    System.out.println("\nEl número de cuenta no ha sido introducido correctamente");
                                }
                                if (numCuentaValido) {
                                    if (gestion.buscarCuenta(numCuentaDestino) == -1 ||
                                            gestion.buscarCuenta(numCuentaOrigen) == -1)
                                        System.out.println("\nAlguna de las cuentas no existen");
                                    else {
                                        if (gestion.getCuenta(gestion.buscarCuenta(numCuentaOrigen)).enRojos())
                                            System.out.println("\n No puede traspasar efectivo de una cuenta en números rojos");
                                        else {
                                            System.out.print("\nIntroduzca la cantidad a traspasar: ");
                                            try {
                                                traspaso = Float.parseFloat(S.nextLine());
                                                System.out.println("¿Se le aplica la comisión de 0.5? S/N");
                                                comision = S.nextLine().toUpperCase();
                                                if (comision.equalsIgnoreCase("S")) {
                                                    gestion.getCuenta(gestion.buscarCuenta(numCuentaOrigen)).reintegro(traspaso, COMISION);
                                                    gestion.getCuenta(gestion.buscarCuenta(numCuentaDestino)).ingreso(traspaso);
                                                } else {
                                                    gestion.getCuenta(gestion.buscarCuenta(numCuentaOrigen)).reintegro(traspaso);
                                                    gestion.getCuenta(gestion.buscarCuenta(numCuentaDestino)).ingreso(traspaso);
                                                }
                                                System.out.println("La transferencia ha sido ejecutada con éxito.");
                                            } catch (Exception e) {
                                                System.out.println("La cantidad no es válida");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("Pulse enter para continuar........");
                    teclaContinuar = S.nextLine();
                    break;

                case 6: //Buscar cuentas en números rojos
                    ArrayList<Cuenta> cuentasenRojos = new ArrayList<>();
                    cuentasenRojos = gestion.buscarCuentasenRojos();
                    System.out.println("\nLas siguientes cuentas bancarias están en números rojos: ");
                    System.out.println("**************************************");
                    for (Cuenta c: cuentasenRojos) {
                        pintarCuenta(c);
                        System.out.println("**************************************");
                    }
                    System.out.println("Pulse enter para continuar........");
                    teclaContinuar = S.nextLine();
                    break;

                case 7: //Ingresar el interes mensual
                    System.out.println("Se va a proceder a ingresar el interés mensual de las cuentas.....");
                    gestion.ingresarInteres();
                    System.out.println("Operación completada");
                    System.out.println("Pulse enter para continuar........");
                    teclaContinuar = S.nextLine();
                    break;

                case 8: //Hacer un bizum
                    System.out.print("Introduzca el móvil que envía el dinero: ");
                    String movilOrigen = S.nextLine();
                    System.out.print("Introduzca el móvil que recibe el dinero: ");
                    String movilDestino = S.nextLine();
                    int posOrigen,posDestino;
                    posOrigen = gestion.buscarCuentaMovil(movilOrigen);
                    posDestino = gestion.buscarCuentaMovil(movilDestino);
                    float envio = 0;
                        if (posOrigen == -1 || posDestino == -1)
                            System.out.println("\nAlguno de los móviles introducidos no están en nuestro sistema");
                        else {
                            if (gestion.getCuenta(posOrigen).enRojos())
                                    System.out.println("\n No puede traspasar efectivo de una cuenta en números rojos");
                            else {
                                System.out.print("\nIntroduzca la cantidad a enviar: ");
                                try {
                                    envio = Float.parseFloat(S.nextLine());
                                    gestion.getCuenta(posOrigen).reintegro(envio);
                                    gestion.getCuenta(posDestino).ingreso(envio);
                                    System.out.println("El bizum ha sido ejecutado con éxito.");
                                } catch (Exception e) {
                                    System.out.println("La cantidad no es válida");
                                }
                            }
                        }
                    break;
            }
        }
    }

    // Funciones auxiliares

    private static Cliente nuevoCliente(String dni){

        System.out.println("\n\nCreación de un nuevo Cliente: ");
        System.out.print("Introduzca el nombre: ");
        String nombre = S.nextLine();
        System.out.print("\nIntroduzca los apellidos: ");
        String apell = S.nextLine();
        System.out.print("\nIntroduzca el número de movil: ");
        String movil = S.nextLine();
        Boolean emailValido = false;
        String email = "";
        while (!emailValido) {
            System.out.print("\nIntroduzca el correo electrónico: ");
            email = S.nextLine();
            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == '@'){
                    emailValido = true;
                }
            }
            if (!emailValido) System.out.println("Correo electrónico erróneo, vuelva a introducirlo");
        }
        Date testDate = new Date();
        Boolean fechaValida = false;
        while (!fechaValida){
            try{
                System.out.print("\nIntroduzca la fecha de nacimiento(dd/MM/yyyy): ");
                var fecha = S.nextLine();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                testDate = null;
                String date = fecha;
                testDate = df.parse(date);
                fechaValida = true;

            } catch (Exception e){
                System.out.println("Fecha de nacimiento en formato no válido");
            }
        }
        Cliente cliente = new Cliente(nombre,apell,dni,movil,email,testDate);
        return cliente;
    }

    private static Boolean comprobarDNI(String dni){
        char letra;
        int num, resto;
        boolean valido = false;
        String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";
        //Compruebo si longitud es 9 y si el ultimo caracter es una letra
        if (dni.length() != 9 || Character.isLetter(dni.charAt(8)) == false ){
            return valido;
        }else{
            letra = dni.charAt(8);
            num = Integer.parseInt(dni.substring(0,8));
            resto = num % 23;
            if (letra == LETRAS.charAt(resto)){
                valido = true;
                return valido;
            }else return valido;
        }
    }

    private static void consultarDatosCliente(GestionCuentas gestion){
        int consulta =0;
        System.out.println("Introduzca opción para la búsqueda de clientes: ");
        System.out.println("1. Por DNI");
        System.out.println("2. Búsqueda en nombre y apellidos");
        try{
            consulta = Integer.parseInt(S.nextLine());
        }catch (Exception e){
            System.out.println("La opción introducida no es válida");
        }
        if (consulta < 3 && consulta >0){
            if (consulta == 1) {
                String dni;
                System.out.print("Introduzca el DNI del cliente: ");
                dni = S.nextLine().toUpperCase();
                ArrayList<Cuenta> cuentasCliente = new ArrayList<>();
                if (!comprobarDNI(dni)) System.out.println("\nHa introducido un DNI no válido");
                else{
                    if (gestion.buscarCliente(dni) == null) System.out.println("No existe ningún cliente con este DNI");
                    else{
                        pintarCliente(gestion.buscarCliente(dni));
                        System.out.println();
                        cuentasCliente = gestion.buscarCuentasCliente(dni);
                        if (cuentasCliente.isEmpty()) System.out.println("Este cliente no tiene cuentas abiertas en nuestro banco");
                        else{
                            System.out.println("Cuentas a nombre de este cliente: ");
                            System.out.println();
                            for (Cuenta c:cuentasCliente) {
                                pintarCuenta(c);
                                System.out.println("**************************************");
                            }
                        }
                    }
                }
            }
            if (consulta == 2) {
                System.out.print("Introduza el término para buscar: ");
                ArrayList<Cliente> clientes = new ArrayList<>();
                System.out.print("Introduza el término para buscar: ");
                String texto = S.nextLine();
                clientes = gestion.buscarClientesTextov2(texto);
                System.out.println("\nHemos encontrado los siguientes clientes: ");
                System.out.println("**************************************");
                for (Cliente c:clientes) {
                    pintarCliente(c);
                    System.out.println("**************************************");
                }
            }
        }
    }

    private static void consultarDatosCuenta(GestionCuentas gestion){
        long numCuenta = -1;
        try{
            System.out.print("Introduzca el número de cuenta a consultar: ");
            numCuenta = Long.parseLong(S.nextLine());
            if (gestion.buscarCuenta(numCuenta) == -1){
                System.out.println("No existe ninguna cuenta con esa numeración");
            }else{
                int pos = gestion.buscarCuenta(numCuenta);
                pintarCuenta(gestion.getCuenta(pos));
            }
        }catch (Exception e){
            System.out.println("El número de cuenta es erróneo");
        }
    }

    private static void pintarCuenta(Cuenta c){
        System.out.println("Información de cuenta bancaria");
        System.out.println("**************************************");
        System.out.println("Número de cuenta:  " + c.getNumero());
        System.out.println("DNI del titular: " + c.getTitular().getDni());
        System.out.println("Saldo: " + c.getSaldo());
        System.out.println("Interés anual: " + c.getInteresAnual());
        System.out.println("Información de los últimos cinco movimientos");
        System.out.println("Fecha \t\t Tipo \t\t Importe \t\t Saldo");
        System.out.println("---------------------------------------------------");
        ArrayList<Cuenta.Movimiento> movimientos = c.getMovimientos();
        int contador = 0;
        for (int i = movimientos.size() - 1; i > -1; i--){
            String fechaFormateada = new SimpleDateFormat("dd-MM-yyyy").format(movimientos.get(i).getFecha());
            System.out.print(fechaFormateada +"\t\t" + movimientos.get(i).getTipo() + "     \t\t" +
                    movimientos.get(i).getImporte() + "\t\t" + movimientos.get(i).getSaldo() + "\n");
            contador++;
            if (contador == 5) break;
        }
    }
    private static void pintarCliente(Cliente c){
        System.out.println("Información de clientes");
        System.out.println("**************************************");
        System.out.println("Nombre del cliente: " + c.getNombre() + " "
                + c.getApellidos());
        System.out.println("DNI: " + c.getDni());
        System.out.println("Móvil: " + c.getNumMovil());
        System.out.println("Email: " + c.getEmail());
        System.out.println("Fecha de nacimiento" + c.getFechNac());
    }

    public static GestionCuentas insertaDatos(){
        GestionCuentas gestion = new GestionCuentas();
        Date fecha;
        Cliente cliente1 = new Cliente("Carlos","Barroso Moriana","77322948F","1234",
                "carlosbarrosomoriana@gmail.com",fecha = new Date());
        Cliente cliente2 = new Cliente("Pepe","Lopez Caro","12345678Z","12345",
                "pepe@pepe",fecha = new Date());
        gestion.añadirCuenta(cliente1,1.5f);
        gestion.añadirCuenta(cliente2, 2.0f);
        return gestion;
    }
}

