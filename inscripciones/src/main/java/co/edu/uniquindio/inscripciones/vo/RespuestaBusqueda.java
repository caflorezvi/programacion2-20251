package co.edu.uniquindio.inscripciones.vo;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RespuestaBusqueda {
    private int posicion;
    private String lista;
}
