package ejercicio1;

import java.util.ArrayList;

public class Ejercicio1 {

    public void mostrarMensaje(ArrayList<Integer> lista){
        System.out.println("La suma es: "+calcularSumaPrimos(lista));
    }

    public int calcularSumaPrimos(ArrayList<Integer> lista){
        int suma = 0;
        for(int numero: lista){
            if( esPrimo(numero) ){
                suma += numero;
            }
        }
        return suma;
    }

    public boolean esPrimo(int numero){

        for(int i = 2; i <= numero/2; i++ ){
            if( numero%i == 0 ){
                return false;
            }
        }

        return true;
    }

}


