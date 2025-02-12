package ejercicio3;

public class Ejercicio3 {

    public void invertirArreglo(int[] arreglo){

        int j = arreglo.length - 1;

        for(int i = 0; i < arreglo.length/2; i++){
            int aux = arreglo[i];
            arreglo[i] = arreglo[j];
            arreglo[j] = aux;
            j--;
        }

    }

}
