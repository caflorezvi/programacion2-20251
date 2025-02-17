package ejercicio1;

import ejercicio2.Ejercicio2;
import ejercicio2.RespuestaListas;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase que contiene diferentes casos de prueba para el ejercicio 2.
 */
public class Ejercicio2Test {

    @Test
    public void esVocalValidaTest(){
        Ejercicio2 ejercicio3 = new Ejercicio2();
        boolean esVocal = ejercicio3.esVocal('U');
        assertTrue(esVocal);
    }

    @Test
    public void esVocalInvalidaTest(){
        Ejercicio2 ejercicio3 = new Ejercicio2();
        boolean esVocal = ejercicio3.esVocal('F');
        assertFalse(esVocal);
    }

    @Test
    public void esConsonanteValidaTest(){
        Ejercicio2 ejercicio3 = new Ejercicio2();
        boolean esConsonante = ejercicio3.esConsonante('N');
        assertTrue(esConsonante);
    }

    @Test
    public void esConsonanteInvalidaTest(){
        Ejercicio2 ejercicio3 = new Ejercicio2();
        boolean esConsonante = ejercicio3.esConsonante('i');
        assertFalse(esConsonante);
    }

    @Test
    public void verificarInicioFinalVocalTest(){
        Ejercicio2 ejercicio3 = new Ejercicio2();
        boolean validacion = ejercicio3.verificarInicioFinalVocal("Abecedario");
        assertTrue(validacion);
    }

    @Test
    public void verificarInicioFinalConsonanteTest(){
        Ejercicio2 ejercicio3 = new Ejercicio2();
        boolean validacion = ejercicio3.verificarInicioFinalConsonante("control");
        assertTrue(validacion);
    }

    @Test
    public void crearListas1Test(){
        Ejercicio2 ejercicio3 = new Ejercicio2();

        ArrayList<String> lista = new ArrayList<>();
        lista.add("casa");
        lista.add("uniquindio");
        lista.add("programacion");
        lista.add("control");
        lista.add("logica");
        lista.add("oso");

        RespuestaListas respuesta = ejercicio3.crearListas(lista);

        assertEquals(2, respuesta.getListaInicioFinVocal().size());
        assertEquals(2, respuesta.getListaInicioFinConsonante().size());
    }

    @Test
    public void crearListas2Test(){
        Ejercicio2 ejercicio3 = new Ejercicio2();

        ArrayList<String> lista = new ArrayList<>( List.of("casa","uniquindio","programacion", "control", "logica", "oso") );

        RespuestaListas respuesta = ejercicio3.crearListas(lista);
        assertEquals( List.of("uniquindio", "oso"), respuesta.getListaInicioFinVocal() );
        assertEquals( List.of("programacion", "control"), respuesta.getListaInicioFinConsonante() );
    }

}
