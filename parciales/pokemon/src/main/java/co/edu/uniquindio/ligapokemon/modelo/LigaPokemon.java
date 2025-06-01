package co.edu.uniquindio.ligapokemon.modelo;

import co.edu.uniquindio.ligapokemon.enums.BatallaNivel;
import co.edu.uniquindio.ligapokemon.factory.Batalla;
import co.edu.uniquindio.ligapokemon.factory.BatallaFactory;
import co.edu.uniquindio.ligapokemon.vo.ResultadoBatalla;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LigaPokemon {

    private final List<Entrenador> entrenadores;
    private final String nombre;
    private final List<Batalla> batallas;

    public LigaPokemon(String nombre) {
        this.nombre = nombre;
        this.batallas = new ArrayList<>();
        this.entrenadores = new ArrayList<>();
    }

    public ResultadoBatalla enfrentar(Equipo e1, Equipo e2, BatallaNivel nivel) {
        Batalla batalla = BatallaFactory.crearBatalla(nivel, e1, e2);
        ResultadoBatalla resultadoBatalla = batalla.ejecutar();
        batallas.add(batalla);
        return resultadoBatalla;
    }

    public void agregarEntrenador(Entrenador entrenador) {
        entrenadores.add(entrenador);
    }

    public String evaluarCompatibilidad(Equipo e1, Equipo e2) {

        List<Pokemon> pokemonList1 = e1.getPokemones();
        List<Pokemon> pokemonList2 = e2.getPokemones();
        int compatibilidad = 0;

        for (Pokemon pokemon1 : pokemonList1) {
            for (Pokemon pokemon2 : pokemonList2) {
                if(pokemon1.getTipo() == pokemon2.getTipo()) {
                    int diferencia = pokemon1.getNivel() - pokemon2.getNivel();

                    if(diferencia >= -1 && diferencia <= 1) {
                        compatibilidad += 3;
                    }else{
                        compatibilidad += 1;
                    }

                }
            }
        }

        float porcentaje =  compatibilidad * 1.f / (pokemonList1.size() * pokemonList2.size() * 3) * 100.f;

        if(porcentaje > 80) {
            return "Alta compatibilidad";
        } else if(porcentaje > 50) {
            return "Compatibilidad moderada";
        } else {
            return "Compatibilidad baja";
        }

    }
}
