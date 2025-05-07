package co.edu.uniquindio.concurso.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fotografo {

    private String cedula;
    private String nombre;
    private float premio;

    @Builder
    public Fotografo(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public void sumarPremio(float premio) {
        this.premio += premio;
    }
}
