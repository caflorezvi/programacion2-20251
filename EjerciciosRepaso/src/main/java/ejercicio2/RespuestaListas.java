package ejercicio2;

import java.util.ArrayList;

public class RespuestaListas {

    private final ArrayList<String> listaInicioFinVocal;
    private final ArrayList<String> listaInicioFinConsonante;

    public RespuestaListas(){
        this.listaInicioFinVocal = new ArrayList<>();
        this.listaInicioFinConsonante = new ArrayList<>();
    }

    public ArrayList<String> getListaInicioFinVocal() {
        return listaInicioFinVocal;
    }

    public ArrayList<String> getListaInicioFinConsonante() {
        return listaInicioFinConsonante;
    }

}
