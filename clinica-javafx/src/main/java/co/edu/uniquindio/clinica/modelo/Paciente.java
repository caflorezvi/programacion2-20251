package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Paciente implements Serializable {

    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private Suscripcion suscripcion;

    @Override
    public String toString() {
        return cedula+" - "+nombre;
    }
}
