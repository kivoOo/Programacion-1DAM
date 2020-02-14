public class main {
    public static void main(String[] args) {
        var dni1 = new DNI();
        if (!dni1.setDNI("77322947V")){
            System.out.println("El DNI: 77322947V no es válido");
        }else{
            System.out.println("El DNI: 77322947V es válido");
            System.out.println("dni1.getNIF() = " + dni1.getNIF());
            System.out.println("dni1.getDNI() = " + dni1.getDNI());
        }

        if (!dni1.setDNI("77322947Y")){
            System.out.println("El DNI: 77322947Y no es válido");
        }else{
            System.out.println("El DNI: 77322947Y es válido");
            System.out.println("dni1.getNIF() = " + dni1.getNIF());
            System.out.println("dni1.getDNI() = " + dni1.getDNI());
        }

    }
}
