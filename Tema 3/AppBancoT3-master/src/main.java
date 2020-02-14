import javax.imageio.IIOException;

public class main {
    public static void main(String[] args) {
        var c1 = new Cuenta(0001, "Carlos Barroso", 1.5f);
        System.out.println("Bienvenido al banco: " + Cuenta.getBanco());
        c1.setSaldo(1000.00f);
        System.out.println("La cuenta c1 con número: " + Long.toString(c1.getNumero()) +
                " y saldo: " + c1.getSaldo() + "€ ha sido creada");
        try{
            c1.salvarCuenta();
            System.out.println("Cuenta grabada en disco");
        } catch (Exception e){
            System.out.println("La cuenta: " + c1.getNumero() + " no ha podido ser salvada");
        }
        c1 = new Cuenta (0002,"Pedro López", 1.3f);
        System.out.println("La cuenta: " + c1.getNumero() + ", tiene de saldo: " + c1.getSaldo() +"€");
        System.out.println("Voy a intentar recuperar la cuenta 0001");
        try{
            c1.salvarCuenta();
            System.out.println("Cuenta grabada en disco");
        } catch (Exception e){
            System.out.println("La cuenta: " + c1.getNumero() + " no ha podido ser salvada");
        }
        try{
            c1 = new Cuenta(0001);
        } catch (Exception e){
            System.out.println("Error al recuperar la cuenta de disco");
        }
        System.out.println("c1.getNumero() = " + c1.getNumero());
        System.out.println("c1.getSaldo() = " + c1.getSaldo());
        


    }
}
