package ejercicio1;

public class Ejercicio3 {

    public boolean esVocal(char letra){
        if(letra == 'a' || letra=='e' || letra=='i' || letra=='o' || letra=='u'){
            return true;
        }
        return false;
    }

    public boolean esConsonante(char letra){
        if( !esVocal(letra) && (letra >= 97 && letra <=122) ) {
            return true;
        }
        return false;
    }

    public boolean verificarInicioFinalVocal(String cadena){
        char inicio = cadena.charAt(0);
        char fin = cadena.charAt( cadena.length()-1 );

        return esVocal(inicio) && esVocal(fin);
    }

    public boolean verificarInicioFinalConsonante(String cadena){
        char inicio = cadena.charAt(0);
        char fin = cadena.charAt( cadena.length()-1 );

        return esConsonante(inicio) && esConsonante(fin);
    }
}
