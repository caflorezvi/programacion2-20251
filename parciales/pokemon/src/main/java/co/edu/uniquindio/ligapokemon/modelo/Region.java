package co.edu.uniquindio.ligapokemon.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Region {
    private String nombre;
    private String descripcion;
    private LigaPokemon liga;
}
