package ejercicio1;

import ejercicio3.Ejercicio3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Clase para probar el método invertirArreglo de la clase Ejercicio3
 */
public class Ejercicio3Test {

    @Test
    public void invertirArregloTest(){
        // Arreglo de prueba
        int[] arreglo = {1,2,3,4,5};
        // Arreglo esperado
        int[] arregloInvertido = {5,4,3,2,1};

        // Instanciar la clase a probar
        Ejercicio3 ejercicio3 = new Ejercicio3();

        // Invertir el arreglo
        ejercicio3.invertirArreglo(arreglo);

        // Verificar que el arreglo invertido sea igual al esperado
        assertArrayEquals(arregloInvertido, arreglo);
    }

}
