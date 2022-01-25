public class DNI {
    //Atributos MIOS!!
    private int numDni;
    private static final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";

    //Metodos
    //getter
    public int getDNI(){
        return numDni;
    }
    public String getNIF() {
        char letra;
        letra = DNI.calcularLetraNIF(numDni);
        String nif = Integer.toString(this.numDni) + letra;
        return nif;
    }
    //setter
    public Boolean setDNI(String nif){
        Boolean resultado = true;
        nif = nif.toUpperCase();
        if (!this.validarNIF(nif)){
            resultado = false;
        }else {
            int numDni = this.extraerNumeroNIF(nif);
            this.numDni = numDni;
        }
        return resultado;
    }
    public Boolean setDNI(int numDni){
        this.numDni = numDni;
        return true;
    }
    //Otros metodos
    private static char calcularLetraNIF(int dni){
        int resto = dni % 23;
        char letra = LETRAS.charAt(resto);
        return letra;
    }
    private static char extraerLetraNIF(String nif){
        char letra;
        letra = nif.charAt(8);
        return letra;
    }
    private static int extraerNumeroNIF(String nif){
        int numero;
        numero = Integer.parseInt(nif.substring(0,8));
        return numero;
    }

    private boolean validarNIF(String nif){
        char letra;
        int num;
        boolean valido = false;
        //Compruebo si longitud es 9 y si el ultimo caracter es una letra
        if (nif.length() != 9 || Character.isLetter(nif.charAt(8)) == false ){
            return valido;
        }
        letra = extraerLetraNIF(nif);
        num = extraerNumeroNIF(nif);
        if (letra == calcularLetraNIF(num)){
            valido = true;
        }
        return valido;
    }
}
