public class main {

    public static void main(String[] args) {
        System.out.println("Bienvenido al catálogo de coches");
        Ruedas michelin = new Ruedas("Michelin","Primacy","225",'V');
        Ruedas dunlop = new Ruedas("Dunlop","Energy","235",'I');

        Coche bmw = new Coche("BMW","320d",177, michelin);

        System.out.println(michelin);
        System.out.println(dunlop);
        System.out.println(bmw);

        System.out.println("Cambio el ancho del objeto michelin a 245");
        michelin.setAncho("245");
        System.out.println("Imprimo el objeto BMW");
        System.out.println(bmw);
    }
}
