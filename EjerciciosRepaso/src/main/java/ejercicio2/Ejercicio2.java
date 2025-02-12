package ejercicio2;

import java.util.ArrayList;

public class Ejercicio2 {

    public boolean esVocal(char letra){
        if(letra == 'a' || letra=='e' || letra=='i' || letra=='o' || letra=='u' ||
                letra == 'A' || letra=='E' || letra=='I' || letra=='O' || letra=='U'){
            return true;
        }
        return false;
    }

    public boolean esConsonante(char letra){
        if( !esVocal(letra) && ( (letra >= 97 && letra <= 122) || (letra >= 65 && letra <= 90) ) ) {
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

    public RespuestaListas crearListas(ArrayList<String> lista){

        RespuestaListas respuesta = new RespuestaListas();

        for(String cadena : lista){
            if( verificarInicioFinalVocal(cadena) ){
                respuesta.getListaInicioFinVocal().add(cadena);
            } else if( verificarInicioFinalConsonante(cadena) ){
                respuesta.getListaInicioFinConsonante().add(cadena);
            }
        }

        return respuesta;
    }
}
