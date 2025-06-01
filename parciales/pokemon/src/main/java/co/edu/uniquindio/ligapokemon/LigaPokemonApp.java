package co.edu.uniquindio.ligapokemon;

import co.edu.uniquindio.ligapokemon.enums.BatallaNivel;
import co.edu.uniquindio.ligapokemon.enums.TipoPokemon;
import co.edu.uniquindio.ligapokemon.modelo.*;
import co.edu.uniquindio.ligapokemon.vo.ResultadoBatalla;

import java.util.List;

public class LigaPokemonApp {

    public static void main(String[] args) {

        Entrenador entrenador1 = Entrenador.builder()
                .nombre("Carlos")
                .build();

        Entrenador entrenador2 = Entrenador.builder()
                .nombre("Pedro")
                .build();

        Equipo equipo1 = Equipo.builder()
                .entrenador(entrenador1)
                .pokemones(List.of(
                        Pokemon.builder()
                                .nombre("Charmander")
                                .tipo(TipoPokemon.FUEGO)
                                .hp(100)
                                .ataque(50)
                                .defensa(30)
                                .nivel(5)
                                .build()
                        ,
                        Pokemon.builder()
                                .nombre("Squirtle")
                                .tipo(TipoPokemon.AGUA)
                                .hp(100)
                                .ataque(40)
                                .defensa(35)
                                .nivel(5)
                                .build()
                        ,
                        Pokemon.builder()
                                .nombre("Bulbasaur")
                                .tipo(TipoPokemon.PLANTA)
                                .hp(100)
                                .ataque(45)
                                .defensa(40)
                                .nivel(5)
                                .build()
                ))
                .build();

        Equipo equipo2 = Equipo.builder()
                .entrenador(entrenador2)
                .pokemones(List.of(
                        Pokemon.builder()
                                .nombre("Seedra")
                                .tipo(TipoPokemon.AGUA)
                                .hp(100)
                                .ataque(55)
                                .defensa(25)
                                .nivel(5)
                                .build()
                        ,
                        Pokemon.builder()
                                .nombre("Magikarp")
                                .tipo(TipoPokemon.AGUA)
                                .hp(100)
                                .ataque(10)
                                .defensa(10)
                                .nivel(5)
                                .build()
                        ,
                        Pokemon.builder()
                                .nombre("Ponyta")
                                .tipo(TipoPokemon.FUEGO)
                                .hp(100)
                                .ataque(60)
                                .defensa(20)
                                .nivel(5)
                                .build()
                ))
                .build();

        LigaPokemon ligaPokemon = new LigaPokemon("Liga de Kanto");
        ResultadoBatalla resultadoBatalla = ligaPokemon.enfrentar(equipo1, equipo2, BatallaNivel.ENTRENAMIENTO);
        System.out.println("Resultado de la batalla: " + resultadoBatalla);

        String compatibilidad = ligaPokemon.evaluarCompatibilidad(equipo1, equipo2);
        System.out.println("Compatibilidad entre los equipos: " + compatibilidad);

    }

}
