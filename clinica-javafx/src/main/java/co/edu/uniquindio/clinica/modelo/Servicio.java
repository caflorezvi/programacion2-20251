package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.enums.TipoServicio;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class Servicio implements Serializable {

    private TipoServicio nombre;
    private double precio;

}
