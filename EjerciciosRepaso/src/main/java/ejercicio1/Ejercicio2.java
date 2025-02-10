package ejercicio1;

import java.util.Arrays;

public class Ejercicio2 {

    public static void main(String[] args) {
        int[] arreglo = {2,3,4,5,7};
        invertirArreglo(arreglo);
        System.out.println(Arrays.toString(arreglo));
    }

    public static void invertirArreglo(int[] arreglo){

        int j = arreglo.length - 1;

        for(int i = 0; i<arreglo.length/2; i++){
            int aux = arreglo[i];
            arreglo[i] = arreglo[j];
            arreglo[j] = aux;
            j--;
        }

    }

}
