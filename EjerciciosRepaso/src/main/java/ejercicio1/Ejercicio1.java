package ejercicio1;

import java.util.ArrayList;

/**
 * Clase que contiene el método para calcular la suma de los números primos de una lista
 */
public class Ejercicio1 {

    public void mostrarMensaje(ArrayList<Integer> lista){
        System.out.println("La suma es: "+calcularSumaPrimos(lista));
    }

    /**
     * Método que calcula la suma de los números primos de una lista
     * @param lista Lista de números
     * @return Suma de los números primos de la lista
     */
    public int calcularSumaPrimos(ArrayList<Integer> lista){
        int suma = 0;
        for(int numero: lista){
            if( esPrimo(numero) ){
                suma += numero;
            }
        }
        return suma;
    }

    /**
     * Método que determina si un número es primo
     * @param numero Número a evaluar
     * @return true si el número es primo, false en caso contrario
     */
    public boolean esPrimo(int numero){

        for(int i = 2; i <= numero/2; i++ ){
            if( numero%i == 0 ){
                return false;
            }
        }

        return true;
    }

}


