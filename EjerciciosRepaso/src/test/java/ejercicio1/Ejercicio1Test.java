package ejercicio1;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase que contiene diferentes casos de prueba para el ejercicio 1.
 */
public class Ejercicio1Test {

    @Test
    public void esPrimoValidoTest(){
        Ejercicio1 e1 = new Ejercicio1();
        boolean respuesta = e1.esPrimo(11);
        assertTrue(respuesta);
    }

    @Test
    public void esPrimoInvalidoTest(){
        Ejercicio1 e1 = new Ejercicio1();
        boolean respuesta = e1.esPrimo(10);
        assertFalse(respuesta);
    }

    @Test
    public void calcularSumaPrimosTest(){
        Ejercicio1 e1 = new Ejercicio1();

        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(2);
        lista.add(7);
        lista.add(11);
        lista.add(10);

        int suma = e1.calcularSumaPrimos(lista);
        assertEquals(20, suma);

    }
}
