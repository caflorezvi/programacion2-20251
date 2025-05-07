package co.edu.uniquindio.inscripciones.modelo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class Aspirante {
    private String numeroIdentificacion;
    private String nombre;
    private LocalDate fechaNacimiento;
    private int puntaje;
}
