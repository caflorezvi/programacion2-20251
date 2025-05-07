package co.edu.uniquindio.concurso.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Foto {

    private String categoria;
    private Fotografo fotografo;
    private List<Float> calificaciones;

    @Builder
    public Foto(String categoria, Fotografo fotografo) {
        this.categoria = categoria;
        this.fotografo = fotografo;
        this.calificaciones = new ArrayList<>();
    }

    public float calcularPromedio(){
        float suma = 0;
        for (Float calificacion : calificaciones) {
            suma += calificacion;
        }
        return suma / calificaciones.size();
    }
}
