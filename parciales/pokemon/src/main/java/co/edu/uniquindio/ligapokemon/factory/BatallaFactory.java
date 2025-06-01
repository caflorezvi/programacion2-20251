package co.edu.uniquindio.ligapokemon.factory;

import co.edu.uniquindio.ligapokemon.enums.BatallaNivel;
import co.edu.uniquindio.ligapokemon.modelo.Equipo;

public class BatallaFactory {

    public static Batalla crearBatalla(BatallaNivel nivel, Equipo e1, Equipo e2) {
        return switch (nivel) {
            case ENTRENAMIENTO -> new BatallaEntrenamiento(e1, e2, 100, 500, false);
            case TORNEO -> new BatallaTorneo(e1, e2, 300, 1500, true);
            case ELITE -> new BatallaElite(e1, e2, 500, 3000, true);
        };
    }
}
