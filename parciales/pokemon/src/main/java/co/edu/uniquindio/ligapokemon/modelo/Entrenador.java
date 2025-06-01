package co.edu.uniquindio.ligapokemon.modelo;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Entrenador {
    private String nombre;
    private int experiencia;
    private int monedas;
    private int victorias;
    private List<Pokemon> pokemones;

    public void incrementarVictorias(){
        this.victorias++;
    }

    public void agregarExperiencia(int xp){
        this.experiencia += xp;
    }

    public void agregarMonedas(int monedas){
        this.monedas += monedas;
    }
}
