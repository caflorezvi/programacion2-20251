package co.edu.uniquindio.ligapokemon.factory;

import co.edu.uniquindio.ligapokemon.modelo.Equipo;
import co.edu.uniquindio.ligapokemon.vo.ResultadoBatalla;

public class BatallaTorneo extends Batalla{

    public BatallaTorneo(Equipo e1, Equipo e2, int experiencia, int monedas, boolean cuentaVictoria) {
        super(e1, e2, experiencia, monedas, cuentaVictoria);
    }

    @Override
    public ResultadoBatalla ejecutar() {
        return simularBatalla();
    }
}