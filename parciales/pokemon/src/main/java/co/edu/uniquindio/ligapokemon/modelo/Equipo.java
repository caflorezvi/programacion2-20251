package co.edu.uniquindio.ligapokemon.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class Equipo {
    private List<Pokemon> pokemones;
    private Entrenador entrenador;
}
