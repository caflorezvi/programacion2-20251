package co.edu.uniquindio.ligapokemon.factory;

import co.edu.uniquindio.ligapokemon.enums.TipoPokemon;
import co.edu.uniquindio.ligapokemon.modelo.Equipo;
import co.edu.uniquindio.ligapokemon.modelo.Pokemon;
import co.edu.uniquindio.ligapokemon.vo.ResultadoBatalla;

import java.util.List;
import java.util.Random;

public abstract class Batalla {

    protected Equipo e1, e2;
    protected int experiencia, monedas;
    protected boolean cuentaVictoria;

    public Batalla(Equipo e1, Equipo e2, int experiencia, int monedas, boolean cuentaVictoria) {
        this.e1 = e1;
        this.e2 = e2;
        this.experiencia = experiencia;
        this.monedas = monedas;
        this.cuentaVictoria = cuentaVictoria;
    }

    public abstract ResultadoBatalla ejecutar();

    protected double calcularVentaja(TipoPokemon t1, TipoPokemon t2) {
        if ((t1 == TipoPokemon.FUEGO && t2 == TipoPokemon.PLANTA) ||
                (t1 == TipoPokemon.AGUA && t2 == TipoPokemon.FUEGO) ||
                (t1 == TipoPokemon.PLANTA && t2 == TipoPokemon.AGUA)) {
            return 1.5;
        }
        return 1.0;
    }

    protected ResultadoBatalla simularBatalla() {
        Equipo equipo1, equipo2;
        Random rand = new Random();
        int turnos = 0;
        boolean turnero = true;

        int inicio = rand.nextInt(2);

        if(inicio == 0){
            System.out.println("Inicia el equipo de " + e1.getEntrenador().getNombre());
            equipo1 = e1;
            equipo2 = e2;
        }else{
            System.out.println("Inicia el equipo de " + e2.getEntrenador().getNombre());
            equipo1 = e2;
            equipo2 = e1;
        }

        int derrotados1 = 0, derrotados2 = 0;
        int acciones = 0;
        Pokemon pokemon1 = elegirAleatorio(equipo1);
        Pokemon pokemon2 = elegirAleatorio(equipo2);

        do {

            System.out.println("Pokemon 1: " + pokemon1.getNombre()+ ", HP: " + pokemon1.getHp());
            System.out.println("Pokemon 2: " + pokemon2.getNombre() + ", HP: " + pokemon2.getHp());

            while(pokemon1.getHp() > 0 && pokemon2.getHp() > 0) {

                if(turnero){
                    atacar(pokemon1, pokemon2);
                }else{
                    atacar(pokemon2, pokemon1);
                }
                turnero = !turnero;
                acciones++;

                if(acciones % 2 == 0) {
                    turnos++;
                }
            }

            if(pokemon1.getHp() <= 0) {
                System.out.println(pokemon1.getNombre()+" ha sido derrotado");
                derrotados1++;
                if (derrotados1 < 3) pokemon1 = elegirAleatorio(equipo1);
            }

            if(pokemon2.getHp() <= 0){
                System.out.println(pokemon2.getNombre()+" ha sido derrotado");
                derrotados2++;
                if (derrotados2 < 3) pokemon2 = elegirAleatorio(equipo2);
            }

            System.out.println("------------------------------");

        }while(derrotados1 != 3 && derrotados2 != 3);

        Equipo equipoGanador = derrotados1 == 3 ? equipo2 : equipo1;

        if(cuentaVictoria) equipoGanador.getEntrenador().incrementarVictorias();
        equipoGanador.getEntrenador().agregarExperiencia(experiencia);
        equipoGanador.getEntrenador().agregarMonedas(monedas);

        return ResultadoBatalla.builder()
                .equipo(equipoGanador)
                .experiencia(experiencia)
                .turnos(turnos)
                .build();
    }

    public void atacar(Pokemon pokemon1, Pokemon pokemon2) {
        int dano = pokemon1.getAtaque() - pokemon2.getDefensa();
        double ventaja = calcularVentaja(pokemon1.getTipo(), pokemon2.getTipo());
        dano = Math.max(1, (int) (dano * ventaja));
        pokemon2.disminurHp(dano);
        System.out.println(pokemon1.getNombre()+" ataca a "+pokemon2.getNombre()+", dano: " + dano);
        System.out.println(pokemon2.getNombre()+" HP: " + pokemon2.getHp());
    }

    public Pokemon elegirAleatorio(Equipo equipo){

        Random rand = new Random();
        List<Pokemon> pokemones = equipo.getPokemones();
        Pokemon seleccionadp = pokemones.get( rand.nextInt(pokemones.size()) );

        while (seleccionadp.getHp() <= 0) {
            seleccionadp = pokemones.get( rand.nextInt(pokemones.size()) );
        }

        return seleccionadp;
    }

}
