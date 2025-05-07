package co.edu.uniquindio.inscripciones.modelo;

import co.edu.uniquindio.inscripciones.enums.TipoCarrera;
import co.edu.uniquindio.inscripciones.vo.RespuestaBusqueda;
import co.edu.uniquindio.inscripciones.vo.RespuestaPorcentajes;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Carrera {

    private TipoCarrera nombre;
    private int puntajeMinino;
    private final ArrayList<Aspirante> listaAdmitidos, listaEspera;

    public Carrera(TipoCarrera nombre, int puntajeMinino) {
        this.nombre = nombre;
        this.puntajeMinino = puntajeMinino;
        listaAdmitidos = new ArrayList<>();
        listaEspera = new ArrayList<>();
    }

    public void guardarAspirante(Aspirante aspirante){

        if(aspirante.getPuntaje() >= puntajeMinino) {

            if(listaAdmitidos.size()==70){
                if(aspirante.getPuntaje() > listaAdmitidos.get(69).getPuntaje()) {
                    listaEspera.add(listaAdmitidos.get(69));
                    listaAdmitidos.remove(69);
                    listaAdmitidos.add(aspirante);
                }else{
                    listaEspera.add(aspirante);
                }
            }else{
                listaAdmitidos.add(aspirante);
            }

        }else{
            listaEspera.add(aspirante);
        }

        ordenar(listaAdmitidos);
        ordenar(listaEspera);

    }

    public RespuestaBusqueda buscarEnListas(String numeroIdentificacion) throws Exception{
        int posicion = buscarPosicionAspirante(listaAdmitidos, numeroIdentificacion);
        if(posicion != -1){
            return new RespuestaBusqueda(posicion, "Admitido");
        }

        posicion = buscarPosicionAspirante(listaEspera, numeroIdentificacion);
        if(posicion != -1){
            return new RespuestaBusqueda(posicion, "En lista de espera");
        }

        throw new Exception("El aspirante no se encuentra registrado");
    }

    private int buscarPosicionAspirante(ArrayList<Aspirante> lista, String numeroIdentificacion){
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getNumeroIdentificacion().equals(numeroIdentificacion)){
                return i+1;
            }
        }
        return -1;
    }

    public void completarLista(){
        if(listaAdmitidos.size() < 70){
            int faltantes = 70 - listaAdmitidos.size();

            if(listaEspera.size() < faltantes){
                faltantes = listaEspera.size();
            }

            for (int i = 0; i < faltantes; i++) {
                Aspirante aspirante = listaEspera.get(0);
                listaAdmitidos.add(aspirante);
                listaEspera.remove(0);
            }
        }
    }

    public RespuestaPorcentajes obtenerPorcentajes(){

        List<Aspirante> todos = new ArrayList<>();
        todos.addAll(listaAdmitidos);
        todos.addAll(listaEspera);

        int menores = 0;
        int hasta25 = 0;
        int superior25 = 0;

        for (Aspirante aspirante : todos) {
            int edad = LocalDate.now().getYear() - aspirante.getFechaNacimiento().getYear();
            if(edad < 18){
                menores++;
            }else if(edad <= 25){
                hasta25++;
            }else {
                superior25++;
            }
        }

        int total = todos.size();

        if (total == 0){
            return new RespuestaPorcentajes(0, 0, 0);
        }

        float porcentajeMenores = (menores * 100.0f) / total;
        float porcentajeHasta25 = (hasta25 * 100.0f) / total;
        float porcentajeSuperior25 = (superior25 * 100.0f) / total;

        return new RespuestaPorcentajes(porcentajeMenores, porcentajeHasta25, porcentajeSuperior25);

    }

    private void ordenar(ArrayList<Aspirante> lista){
        lista.sort((a1, a2) -> a2.getPuntaje() - a1.getPuntaje());
    }

    public float obtenerDiferenciaPuntajes(){
        if(listaAdmitidos.isEmpty()){
            return 0;
        }
        return listaAdmitidos.get(0).getPuntaje() - listaAdmitidos.get(listaAdmitidos.size()-1).getPuntaje();
    }

}
