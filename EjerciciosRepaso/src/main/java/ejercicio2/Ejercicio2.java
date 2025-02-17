package ejercicio2;

import java.util.ArrayList;

/**
 * Clase que contiene los métodos necesarios para resolver el ejercicio 2. El cual consiste en crear dos listas,
 * una con las palabras que inician y terminan con vocal y otra con las palabras que inician y terminan con consonante.
 */
public class Ejercicio2 {

    /**
     * Método que verifica si una letra es vocal.
     * @param letra Letra a verificar
     * @return true si la letra es vocal, false en caso contrario
     */
    public boolean esVocal(char letra){
        if(letra == 'a' || letra=='e' || letra=='i' || letra=='o' || letra=='u' ||
                letra == 'A' || letra=='E' || letra=='I' || letra=='O' || letra=='U'){
            return true;
        }
        return false;
    }

    /**
     * Método que verifica si una letra es consonante. Se usa un rango de valores ASCII para verificar si la letra es una letra del alfabeto.
     * @param letra Letra a verificar
     * @return true si la letra es consonante, false en caso contrario
     */
    public boolean esConsonante(char letra){
        if( !esVocal(letra) && ( (letra >= 97 && letra <= 122) || (letra >= 65 && letra <= 90) ) ) {
            return true;
        }
        return false;
    }

    /**
     * Método que verifica si una cadena inicia y termina con vocal.
     * @param cadena Cadena a verificar
     * @return true si la cadena inicia y termina con vocal, false en caso contrario
     */
    public boolean verificarInicioFinalVocal(String cadena){
        char inicio = cadena.charAt(0);
        char fin = cadena.charAt( cadena.length()-1 );

        return esVocal(inicio) && esVocal(fin);
    }

    /**
     * Método que verifica si una cadena inicia y termina con consonante.
     * @param cadena Cadena a verificar
     * @return true si la cadena inicia y termina con consonante, false en caso contrario
     */
    public boolean verificarInicioFinalConsonante(String cadena){
        char inicio = cadena.charAt(0);
        char fin = cadena.charAt( cadena.length()-1 );

        return esConsonante(inicio) && esConsonante(fin);
    }

    /**
     * Método que crea dos listas, una con las palabras que inician y terminan con vocal y otra con las palabras que inician y terminan con consonante.
     * @param lista
     * @return
     */
    public RespuestaListas crearListas(ArrayList<String> lista){

        // Crear objeto de respuesta
        RespuestaListas respuesta = new RespuestaListas();

        // Recorrer la lista de palabras y verificar si inician y terminan con vocal o consonante para agregarlas a la lista correspondiente
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
