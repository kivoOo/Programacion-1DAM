public class PersonaPrueba {

    public static void main(String[] args) {
        //Creamos un objeto de tipo persona

        var persona1 = new Persona("Carlos","Barroso","Moriana",36,
                12345678,'H',false);
        var persona2 = new Persona("Pepe","Lopez","Garrido",25,
                87654321,'H',true);
        var persona3 = new Persona("Lola","Castro","Marchal",30,
                652278325,'M',true);
        try{
            persona1.salvar();
            persona2.salvar();
            persona3.salvar();
        }catch (Exception e){
            e.printStackTrace();
        }
        var persona11 = new Persona();
        var persona12 = new Persona();
        var persona13 = new Persona();
        try{
            persona11 = persona11.leerPersonaDisco(persona1.getNombre() + ".dat");
            persona12 = persona12.leerPersonaDisco(persona2.getNombre() + ".dat");
            persona13 = persona13.leerPersonaDisco(persona3.getNombre() + ".dat");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(persona11);
        System.out.println(persona12);
        System.out.println(persona13);
    }
}
