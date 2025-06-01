package co.edu.uniquindio.ligapokemon.vo;

import co.edu.uniquindio.ligapokemon.modelo.Entrenador;
import co.edu.uniquindio.ligapokemon.modelo.Equipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class ResultadoBatalla {
    private Equipo equipo;
    private int turnos;
    private int experiencia;
}
