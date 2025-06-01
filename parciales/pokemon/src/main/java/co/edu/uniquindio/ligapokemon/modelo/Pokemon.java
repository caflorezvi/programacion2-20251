package co.edu.uniquindio.ligapokemon.modelo;

import co.edu.uniquindio.ligapokemon.enums.TipoPokemon;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Pokemon {
    private String nombre;
    private TipoPokemon tipo;
    private int hp;
    private int ataque;
    private int defensa;
    private int nivel;

    public void disminurHp(int dano){
        this.hp -= dano;
    }

    public void restaurarHp(){
        this.hp = 100;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", hp=" + hp +
                '}';
    }
}
