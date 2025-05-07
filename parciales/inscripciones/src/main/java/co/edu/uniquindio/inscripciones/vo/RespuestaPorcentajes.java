package co.edu.uniquindio.inscripciones.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class RespuestaPorcentajes {
    private float porcentajeMenores;
    private float porcentajeHasta25;
    private float porcentajeSuperior25;

}
