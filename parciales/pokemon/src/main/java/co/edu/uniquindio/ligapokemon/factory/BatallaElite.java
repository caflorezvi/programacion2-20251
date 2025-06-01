package co.edu.uniquindio.ligapokemon.factory;

import co.edu.uniquindio.ligapokemon.modelo.Equipo;
import co.edu.uniquindio.ligapokemon.vo.ResultadoBatalla;

public class BatallaElite extends Batalla{

    public BatallaElite(Equipo e1, Equipo e2, int experiencia, int monedas, boolean cuentaVictoria) {
        super(e1, e2, experiencia, monedas, cuentaVictoria);
        if (e1.getEntrenador().getVictorias() < 5 || e2.getEntrenador().getVictorias() < 5) {
            throw new IllegalArgumentException("Ambos entrenadores deben tener al menos 5 victorias para batalla élite");
        }
    }

    @Override
    public ResultadoBatalla ejecutar() {
        return simularBatalla();
    }
}